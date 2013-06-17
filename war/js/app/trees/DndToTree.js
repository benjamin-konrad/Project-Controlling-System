define([ "dojo/_base/declare", "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/tree/dndSource", "dojo/aspect",
		"dojo/store/Observable", "dojo/_base/lang" ], function(declare, Tree, Memory, ObjectStoreModel, dndSource, aspect, Observable, lang) {
	return declare([], {

		constructor : function(nodeId, externalStore) {
			this.externalStore = externalStore;
			var that = this;
			var store = new Memory({
				data : [ {
					id : 'filter',
					name : 'Filter'
				} ],
				getChildren : function(object) {
					return this.query({
						parent : object.id
					});
				}
			});

			this.observableStore = new Observable(store);
			
			aspect.around(this.observableStore, "put", function(originalPut) {
				that.originalPut = originalPut;
				return lang.hitch(that, that._put);
			});

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
			// This Problem occurs only if the tree already contains the item with the user tries to add by drag and drop!
			// The Workaround doesn't allow to move TreeItems through the Tree!
			aspect.before(this.tree.dndController, "onDndDrop", function(source, nodes, copy) {
				that._onDndDrop(source, nodes, copy);
			});
			
			this.tree.onDblClick = this._onDblClick;
			this.tree.startup();
		},

		_put : function(obj, options) {
			if (options && options.parent) {
				obj.parent = options.parent.id;
			}

			var leafs = this.externalStore.getChildren(obj);

			for ( var i = 0; i < leafs.length; i++) {
				this.model.newItem(leafs[i], obj);
			}

			return this.originalPut.call(this.observableStore, obj, options);

		},

		_onDndDrop : function(source, nodes, copy) {
			for(var i = 0; i < nodes.length; i++){
				var sourceItem = source.getItem(nodes[i].id);
				var childItem = sourceItem.data.item;
				var ident = this.observableStore.get(childItem.id);
				if( !(ident == undefined) )
					nodes.length = 0;
			}
		},

		_onDblClick : function(item, node, evt) {
			node.tree.model.store.remove(item.id);
		},

	});
});