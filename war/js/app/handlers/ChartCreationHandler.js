define([ "dojo/_base/declare", "dojo", "dijit/registry", "app/portlets/ChartPortlet", "app/handlers/ChartEditHandler", "dojo/store/Memory",
		"dojo/store/Observable", "dojo/on" ], function(declare, dojo, registry, ChartPortlet, ChartEditHandler, Memory, Observable, on) {
	return declare([], {

		constructor : function(portletIdToPortlet, RestTree, dndTree) {
			this.restTree = RestTree;
			this.dndTree = dndTree;
			this.portletIdToPortlet = portletIdToPortlet;
			this.editHandler = new ChartEditHandler(this, RestTree, dndTree);
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

			// TODO: ADD
			this.restTree.fetchData();

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

			this.widgetStore = new Observable(dndStore);
			this.dndTree.setStore(this.widgetStore);
		},

		onClickChartSecondStepCreate : function() {
			var port = new ChartPortlet({
				title : this.chartName
			});
			registry.byId("portletGrid").addChild(port);
			var portletId = port.init(this.typeOfFilter, this.typeOfKennzahl, this.typeOfChart, this.editHandler, this.widgetStore);
			this.portletIdToPortlet[portletId] = port;
			this.btn_firstStepCommitOnClick.remove();
			this.btn_secondStepCommitOnClick.remove();
		}
	});
});