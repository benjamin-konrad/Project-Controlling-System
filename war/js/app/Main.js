function main() {
	require([ "dojo/store/Memory", "dijit/registry", "dojo/store/Observable", "app/trees/DndTree", "app/trees/RestTree", "app/handlers/click/ChartCreationHandler", "dojo/on",
			"dojo" ], function(Memory, registry, Observable, DndTree, RestTree, ChartCreationHandler, on, dojo) {
		setTimeout(function() {
			dojo.fadeOut({
				node : "splashScreen",
				duration : 750,
				onEnd : function() {
					dojo.style("splashScreen", "display", "none");
				}
			}).play();
			
		}, 3000);
		window.filterTypes = {
			"ZEIT" : [ "year", "month", "quarter" ],
		}
		window.id = 0;
		window.chartMinWidth = null;
		window.chartMinHeight = null;
		window.chartTheme = "Julie";
		var portletIdToPortlets = {};
		registry.byId("select_chartTheme").set("value", window.chartTheme);
		on(registry.byId("btn_dlg_Settings_ok"), "click", function() {
			window.chartTheme = registry.byId("select_chartTheme").get("value");
			for ( var key in portletIdToPortlets) {
				if (portletIdToPortlets.hasOwnProperty(key)) {
					portletIdToPortlets[key].refreshChart();
				}
			}

		});
		registry.byId("pane_setting_gridContainer").disableDnd();
		registry.byId("pane_FirstStepGridLayout").disableDnd();
		var restStore = new Memory({
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

		var restObservableStore = new Observable(restStore);
		var restTree = new RestTree("tree_filter_out", restObservableStore, "testfile2.json");
		var dndStore = new Memory({
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

		var dndObservableStore = new Observable(dndStore);
		var dndTree = new DndTree("tree_filter_in", dndObservableStore, restObservableStore);
		var handler = new ChartCreationHandler(portletIdToPortlets, restTree, dndTree);

		restTree.fetchData();

		on(registry.byId("tb_menubar_NewChart"), "click", function() {
			handler.beforeOnClickChartFirstStepCreate();
		});
	});

}