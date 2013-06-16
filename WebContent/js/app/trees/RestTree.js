define([ "dojo/_base/declare", "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/tree/dndSource", "dojo/aspect",
		"dojo/store/Observable", "dojo/_base/lang", "dojo/request" ], function(declare, Tree, Memory, ObjectStoreModel, dndSource, aspect, Observable, lang, request) {
	return declare([], {

		constructor : function(nodeId, restUrl) {
			this.restUrl = restUrl;
			store = new Memory({
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
		
		getTreeStore : function(){
			return this.observableStore;
		},
		
		fetchData : function(){
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
		
		showKennzahl :  function(kennzahl){
			
		},

	});
});