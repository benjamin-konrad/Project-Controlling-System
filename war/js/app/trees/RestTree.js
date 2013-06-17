define([ "dojo/_base/declare", "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/tree/dndSource", "dojo/aspect",
		"dojo/store/Observable", "dojo/_base/lang", "dojo/request" ], function(declare, Tree, Memory, ObjectStoreModel, dndSource, aspect, Observable, lang, request) {
	return declare([], {

		constructor : function(nodeId, store, restUrl) {
			this.restUrl = restUrl;
			
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
			
			// Disable drop for Tree -> Only drags allowed!
			aspect.before(this.tree.dndController, "onDndDrop", function(originalDrop) {
				return function() {};
			});
			
			this.tree.startup();
		},
		
		fetchData : function(){
			this.refreshStore();
			var _this = this;
			request(this.restUrl).then(function(text){
				var obj = JSON.parse(text);
				var objectArray = obj.entries;
				for(var i = 0; i < objectArray.length; i++){
					var object = objectArray[i];
					var parents = _this.observableStore.query({
						id : object.parent
					});
					_this.model.newItem(object, parents[0]);
				}
				console.log(text);
			}, function(error){
				console.log("error: " + error);
			});
		},
		
		refreshStore : function(){
			this.tree.dndController.selectNone(); 
		     
			this.tree._itemNodesMap = {};
			this.tree.rootNode.state = "UNCHECKED";
			this.tree.model.root.children = null;

		  
			this.tree.rootNode.destroyRecursive();

		   
			this.tree.model.constructor(this.tree.model)

		   
			this.tree.postMixInProperties();
			this.tree._load();
		},
		
		showKennzahl :  function(kennzahl){
			
		},

	});
});