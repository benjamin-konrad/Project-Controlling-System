function main() {
	require([ "dijit/Tree", "dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/registry", "app/portlets/ChartPortlet", "dijit/tree/dndSource",
			"dojo/aspect", "dojo/store/Observable", "dojo/dnd/Source", "app/trees/DndToTree", "app/trees/RestTree" ], function(Tree, Memory, ObjectStoreModel, registry, ChartPortlet, dndSource, aspect,
			Observable, DndSource, DndToTree, RestTree) {
		window.id = 0;
		window.chartMinWidth = null;
		window.chartMinHeight = null;
		/*
		var myStore = new Memory({
			data : [ {
				id : 'filter',
				name : 'Zeitfilter',
			}, {
				id : '2010',
				name : '2010',
				parent : 'filter'
			}, {
				id : '2010Q1',
				name : 'Q1',
				parent : '2010'
			}, {
				id : '2010Januar',
				name : 'Januar',
				parent : '2010Q1'
			}, {
				id : '2010Q2',
				name : 'Q2',
				parent : '2010'
			}, {
				id : '2010Maerz',
				name : 'März',
				parent : '2010Q2'
			}, {
				id : '2009',
				name : '2009',
				parent : 'filter'
			},  {
				id : '2009Q1',
				name : 'Q1',
				parent : '2009'
			}, {
				id : '2009Januar',
				name : 'Januar',
				parent : '2009Q1'
			}, {
				id : '2009Q2',
				name : 'Q2',
				parent : '2009'
			}, {
				id : '2009Maerz',
				name : 'März',
				parent : '2009Q2'
			}],
			getChildren : function(object) {
				return this.query({
					parent : object.id
				});
			}
		});

		
		aspect.around(myStore, "put", function(originalPut) {
			// To support DnD, the store must support put(child, {parent:
			// parent}).
			// Since memory store doesn't, we hack it.
			// Since our store is relational, that just amounts to setting
			// child.parent
			// to the parent's id.
			return function(obj, options) {
				if (options && options.parent) {
					obj.parent = options.parent.id;
				}
				return originalPut.call(myStore, obj, options);
			};
		});

		var observableStore = new Observable(myStore);

		// Create the model
		var myModel = new ObjectStoreModel({
			store : observableStore,
			query : {
				id : 'filter'
			}
		});

		// Create the Tree.
		var tree = new Tree({
			model : myModel,
			dndController : dndSource
		}, "tree_filter_out")
		tree.startup();
		
		aspect.before(tree.dndController, "onDndDrop", function(originalPut) {
			// To support DnD, the store must support put(child, {parent:
			// parent}).
			// Since memory store doesn't, we hack it.
			// Since our store is relational, that just amounts to setting
			// child.parent
			// to the parent's id.
			return function(obj, options) {
				if (options && options.parent) {
					obj.parent = options.parent.id;
				}
				return originalPut.call(myStore, obj, options);
			};
		});*/
		var restStore = new RestTree("tree_filter_out", "testfile.json");
		
		new DndToTree("tree_filter_in", restStore.getTreeStore());
		
		restStore.fetchData();
		/*
		 * dialog = registry.byId("dlg_createChart_SecondStep");
		 * setInterval(function(){ dialog.resize(); }, 1000);
		 */
		var logoutButton = registry.byId("tb_menubar_logout");
		logoutButton.on("click", function() {
			require([ "app/portlets/ChartPortlet" ], function(ChartPortlet) {
				var port = new ChartPortlet({
					title : "Statistik Chart"
				});
				registry.byId("portletGrid").addChild(port);
				port.init();
			});
		});
		setInterval(function() {
			registry.byId("mainContainer").resize();
		}, 1000);
	});

}