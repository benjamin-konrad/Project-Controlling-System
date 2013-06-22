define([ "dojo/_base/declare", "dojo", "dijit/registry", "dojo/on" ], function(declare, dojo, registry, on) {
	return declare([], {

		constructor : function(dataFetcher,  restTree, dndTree) {
			this.dataFetcher = dataFetcher;
			this.dndTree = dndTree;
			this.restTree = restTree;
		},

		beforeShowFirstStepDialog : function(portlet, empty) {
			var _this = this;
			var filter = "ZEIT", kennzahl = "LEISTUNG", chart = "ClusteredBars", name = "";
			if (empty === false) {
				filter = portlet.getTypeOfFilter();
				kennzahl = portlet.getTypeOfKennzahl();
				name = portlet.getName();
				chart = portlet.getTypeOfChart();
				this.btn_firstStepCommitOnClick = on(registry.byId("btn_firstStepCommit"), "click", function() {
					_this.onClickChartFirstStepEdit(portlet);
				});
				this.btn_secondStepCommitOnClick = on(registry.byId("btn_secondStepCommit"), "click", function() {
					_this.onClickChartSecondStepEdit(portlet);
				});
				registry.byId("select_Filter").set('disabled', true);
				registry.byId("select_Kennzahl").set("disabled", true);
			} else {
				registry.byId("select_Filter").set('disabled', false);
				registry.byId("select_Kennzahl").set("disabled", false);
			}
			registry.byId("select_Filter").set("value", filter);
			registry.byId("select_Kennzahl").set("value", kennzahl);
			registry.byId("txt_chartName").set("value", name);
			registry.byId("select_Chart").set("value", chart);

			if(empty === false)
				registry.byId("dlg_createChart_FirstStep").show();
		},

		onClickChartFirstStepEdit : function(portlet) {
			this.chartName = registry.byId("txt_chartName").get("value");
			this.typeOfChart = registry.byId("select_Chart").get("value");
			this.restTree.fetchData();
			this.dndTree.setStore(portlet.getStore());
		},
		
		onClickChartSecondStepEdit : function(portlet){
			portlet.setName(this.chartName);
			portlet.setTypeOfChart(this.typeOfChart);
			this.chartDataFetcher.fetchData(this.typeOfFilter, this.typeOfKennzahl, this.widgetStore, this.RestTree.model.store, function(dataStore){				
				portlet.refreshChart(dataStore);
			}, portlet.getDataStore());
			this.btn_firstStepCommitOnClick.remove();
			this.btn_secondStepCommitOnClick.remove();
		}
	});
});