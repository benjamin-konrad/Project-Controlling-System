define([ "dojo/_base/declare", "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/tree/dndSource", "dojo/aspect", "dojo/store/Observable", "dojo/_base/lang",
		"dojo/request", "dojo", "dijit/registry" ], function(declare, Tree, Memory, ObjectStoreModel, dndSource, aspect, Observable, lang, request, dojo, registry) {
	return declare([], {

		constructor : function(nodeId, store, restUrl) {
			this.restUrl = restUrl;

			this.observableStore = store;

			this.model = new ObjectStoreModel({
				store : this.observableStore,
				query : {
					id : "filter"
				}
			});

			this.tree = new Tree({
				model : this.model,
				dndController : dndSource
			}, nodeId);

			// Disable drop for Tree -> Only drags allowed!
			aspect.before(this.tree.dndController, "onDndDrop", function(originalDrop) {
				return function() {
				};
			});

			this.tree.startup();
		},

		fetchData : function(filterType) {
			dojo.style("ajaxLoaderContainer", "display", "block");
			dojo.style("tableTree", "display", "none");
			registry.byId("resizePane").resize({
				h : 100,
				w : 200
			});
			var _this = this;
			var url = this.restUrl;
			switch (filterType) {
			case "ZEIT":
				url += "getTimeFilterList";
				break;
			case "MITARBEITER":
				url += "getMitarbeiterFilterList";
				break;
			case "ORGANISATION":
				url += "getOrganisationFilterList";
				break;
			}
			;
			request(url).then(function(json) {
				var obj = JSON.parse(json);
				var objectArray = obj.possibleThingis;
				for ( var i = 0; i < objectArray.length; i++) {
					var object = objectArray[i];
					_this.observableStore.put(object);
				}
				dojo.style("ajaxLoaderContainer", "display", "none");
				dojo.style("tableTree", "display", "block");
				_this.refreshStore();
			}, function(error) {
				console.log("error: " + error);
			});
		},

		refreshStore : function() {
			this.tree.dndController.selectNone();

			this.tree._itemNodesMap = {};
			this.tree.rootNode.state = "UNCHECKED";
			this.tree.model.root.children = null;

			this.tree.rootNode.destroyRecursive();

			this.tree.model.constructor(this.tree.model)

			this.tree.postMixInProperties();
			this.tree._load();
		},

		showTree : function(filterType) {
			if (window.filter !== filterType) {
				this.fetchData(filterType);
				window.filter = filterType;
			}
		},

	});
});