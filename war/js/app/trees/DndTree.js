define(
		[ "dojo/_base/declare", "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/tree/dndSource", "dojo/aspect", "dojo/store/Observable", "dojo/_base/lang" ],
		function(declare, Tree, Memory, ObjectStoreModel, dndSource, aspect, Observable, lang) {
			return declare(
					[],
					{

						constructor : function(nodeId, store, externalStore) {
							this.storeToMaxType = {};
							this.externalStore = externalStore;
							var that = this;
							this.observableStore = store;

							this.model = new ObjectStoreModel({
								store : this.observableStore,
								query : {
									id : 'filter'
								}
							});

							this.tree = new Tree({
								model : this.model,
								dndController : dndSource
							}, nodeId);

							// Workaround for disappearing TreeItems.
							// This Problem occurs only if the tree already
							// contains the item with the user tries to add by
							// drag and drop!
							// The Workaround doesn't allow to move TreeItems
							// through the Tree!
							aspect.before(this.tree.dndController, "onDndDrop", function(source, nodes, copy) {
								that._beforeOnDndDrop(source, nodes, copy);
							});

							aspect.after(this.tree.dndController, "onDndDrop", function(dummy, args) {
								that._afterOnDndDrop(args[0], args[1]);
							});

							this.tree.onDblClick = lang.hitch(this, this._onDblClick);
							this.tree.startup();
						},

						setStore : function(store) {
							this.observableStore = store;
							var that = this;

							aspect.around(this.observableStore, "put", function(originalPut) {
								that.originalPut = originalPut;
								return lang.hitch(that, that._put);
							});

							this.tree.dndController.selectNone();
							this.tree.model.store = store;

							this.tree._itemNodesMap = {};
							this.tree.rootNode.state = "UNCHECKED";
							this.tree.model.root.children = null;

							this.tree.rootNode.destroyRecursive();

							this.tree.model.constructor(this.tree.model)

							this.tree.postMixInProperties();
							this.tree._load();
						},

						_put : function(obj, options) {
							if (options && options.parent) {
								obj.oldParent = obj.parent;
								obj.parent = options.parent.id;
							}

							return this.originalPut.call(this.observableStore, obj, options);

						},

						_afterOnDndDrop : function(source, nodes, copy) {
							if (nodes.length > 0) {
								var sourceItem = source.getItem(nodes[0].id);
								var childItem = sourceItem.data.item;
								var type = childItem.fgruppe;
								var types = window.filterTypes[type];
								this._lookForMaxType(types);
							}
						},
						
						_lookForMaxType : function(types){
							for ( var i = 0; i < types.length; i++) {
								var foundData = this.observableStore.query({
									fident : types[i]
								});
								if (foundData.length > 0)
									this.observableStore.put({
										id : "maxType",
										maxType : types[i]
									})
							}
						},

						_beforeOnDndDrop : function(source, nodes, copy) {
							for ( var i = 0; i < nodes.length; i++) {
								var sourceItem = source.getItem(nodes[i].id);
								var childItem = sourceItem.data.item;
								var ident = this.observableStore.get(childItem.id);
								if (!(ident === undefined)
										|| (this.observableStore.get("maxType") !== undefined && this.observableStore.get("maxType").maxType != undefined && childItem.fident !== this.observableStore
												.get("maxType").maxType))

									nodes.length = 0;
							}
						},

						_onDblClick : function(item, node, evt) {
							node.tree.model.store.remove(item.id);
							if (node.tree.model.store.data === 0)
								this.observableStore.get("maxType").maxType = undefined;
							else
								this._lookForMaxType(window.filterTypes[item.fgruppe]);
						},

					});
		});