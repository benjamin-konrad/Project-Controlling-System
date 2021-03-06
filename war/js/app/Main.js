function main() {
	require([ "dojo/store/Memory", "dijit/registry", "dojo/store/Observable", "app/trees/DndTree", "app/trees/RestTree", "app/handlers/click/ChartCreationHandler", "dojo/on",
			"dojo", "dojo/_base/array" ], function(Memory, registry, Observable, DndTree, RestTree, ChartCreationHandler, on, dojo, array) {
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
			"ZEIT" : [ "JAHR", "MONAT", "QUARTAL" ],
			"MITARBEITER" : ["ENTWICKLUNGSSTUFE", "MITARBEITER"],
			"ORGANISATION": ["BEREICH", "PROJEKT", "KONTO"]
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
					portletIdToPortlets[key].refreshChartTheme();
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
				var result =  this.query({
					parent : object.id
				});
				result = array.filter(result, function(item){
					return window.filter === item.fgruppe;
				});
				return result;
			}
		});

		var restObservableStore = new Observable(restStore);
		var restTree = new RestTree("tree_filter_out", restObservableStore, "http://pro-con-sys.appspot.com/api/filter/");
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

		//restTree.fetchData();

		on(registry.byId("tb_menubar_NewChart"), "click", function() {
			handler.beforeOnClickChartFirstStepCreate();
		});

		on(registry.byId("dlg_createChart_FirstStep"), "hide", function(){
			handler.onClose(true);
		});
		
		on(registry.byId("dlg_createChart_SecondStep"), "hide", function(){
			handler.onClose(false);
		});
	});

}