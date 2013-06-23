define([ "dojo/_base/declare", "dojo", "dijit/registry", "app/portlets/ChartPortlet", "app/handlers/click/ChartEditHandler", "dojo/store/Memory", "dojo/store/Observable",
		"dojo/on", "app/handlers/network/ChartDataFetcher" ], function(declare, dojo, registry, ChartPortlet, ChartEditHandler, Memory, Observable, on, ChartDataFetcher) {
	return declare([], {

		constructor : function(portletIdToPortlet, RestTree, dndTree) {
			this.restTree = RestTree;
			this.dndTree = dndTree;
			this.portletIdToPortlet = portletIdToPortlet;
			this.chartDataFetcher = new ChartDataFetcher("http://pro-con-sys.appspot.com/api/filter");
			this.editHandler = new ChartEditHandler(this.chartDataFetcher, RestTree, dndTree);
		},

		beforeOnClickChartFirstStepCreate : function() {
			this.editHandler.beforeShowFirstStepDialog(true);
			var _this = this;
			this.btn_firstStepCommitOnClick = on(registry.byId("btn_firstStepCommit"), "click", function() {
				_this.onClickChartFirstStepCreate();
			});
			this.btn_secondStepCommitOnClick = on(registry.byId("btn_secondStepCommit"), "click", function() {
				_this.onClickChartSecondStepCreate();
			});
		},

		onClickChartFirstStepCreate : function() {
			this.typeOfFilter = registry.byId("select_Filter").get("value");
			this.typeOfKennzahl = registry.byId("select_Kennzahl").get("value");
			this.chartName = registry.byId("txt_chartName").get("value");
			this.typeOfChart = registry.byId("select_Chart").get("value");

			this.restTree.showTree(this.typeOfFilter);
			this.restTree.fetchData();

			var dndStore = new Memory({
				data : [ {
					id : 'filter',
					name : 'Filter',
					filter : this.typeOfFilter
				} ],
				getChildren : function(object) {
					return this.query({
						parent : object.id
					});
				}
			});

			this.widgetStore = new Observable(dndStore);
			this.dndTree.setStore(this.widgetStore);
		},

		onClickChartSecondStepCreate : function() {
			var port = new ChartPortlet({
				title : this.chartName,
				isLoaded : false
			});
			port.doPostCreate();
			registry.byId("portletGrid").addChild(port);
			var _this = this;
			this.chartDataFetcher.fetchData(this.typeOfFilter, this.typeOfKennzahl, this.widgetStore, this.restTree.model.store, function(dataStore){				
				var portletId = port.init(_this.typeOfFilter, _this.typeOfKennzahl, _this.typeOfChart, _this.editHandler, dataStore);
				_this.portletIdToPortlet[portletId] = port;
			});
			this.btn_firstStepCommitOnClick.remove();
			this.btn_secondStepCommitOnClick.remove();
		}
	});
});