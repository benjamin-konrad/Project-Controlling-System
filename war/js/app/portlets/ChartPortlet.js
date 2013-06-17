define([ "dojo/_base/declare", "dojo", "dojox/widget/Portlet", "dojox/layout/ResizeHandle", "dijit/layout/ContentPane", "app/portlets/ChartWidget",
		"dojo/_base/lang" ], function(declare, dojo, Portlet, ResizeHandle, ContentPane, ChartWidget, lang) {
	return declare([ Portlet ], {

		constructor : function() {

		},

		init : function(typeOfFilter, typeOfKennzahl, typeOfChart, editHandler, widgetStore) {
			this.editHandler = editHandler;
			this.typeOfFilter = typeOfFilter;
			this.typeOfKennzahl = typeOfKennzahl;
			this.typeOfChart = typeOfChart;
			this.widgetStore = widgetStore;
			this._createIcon("dojoxPortletSettingsIcon", "dojoxPortletSettingsIconHover", lang.hitch(this, this._onClickEdit));

			var idOfPane = "portletContentPane" + window.id++; // +
			var idOfChart = idOfPane + window.id;
			var idOfLegend = idOfChart + "Legend";

			var contentPane = new ContentPane({
				id : idOfPane,
				content : "<div id='" + idOfChart + "'></div>",
				doLayout : true
			});
			this.addChild(contentPane);
			contentPane.startup();

			contentPane = new ContentPane({
				content : "<div id='" + idOfLegend + "'></div>",
				doLayout : true
			});
			this.addChild(contentPane);
			contentPane.startup();

			this.chart = new ChartWidget(typeOfChart, idOfChart, idOfLegend, widgetStore);

			var startTopic = idOfChart + "Start";
			var endTopic = idOfChart + "End";

			var handle = new dojox.layout.ResizeHandle({
				targetId : idOfPane,
				endTopic : endTopic,
				startTopic : startTopic,
				minHeight : window.chartMinHeight,
				minWidth : window.chartMinWidth
			});
			this.addChild(handle);
			handle.domNode.classList.add("resizeHandleChart");
			handle.startup();

			this.resize(window.chartMinWidht, window.chartMinHeight);

			return this.id;
		},

		refreshChart : function() {
			this.chart.refreshTheme();
		},

		getStore : function() {
			return this.widgetStore;
		},

		_onClickEdit : function() {
			this.editHandler.beforeShowFirstStepDialog(this, false);
		},

		getTypeOfFilter : function() {
			return this.typeOfFilter;
		},

		getTypeOfKennzahl : function() {
			return this.typeOfKennzahl;
		},

		getTypeOfChart : function() {
			return this.typeOfChart;
		},

		getName : function() {
			return this.title;
		},

		setName : function(name) {
			this.set("title", name);
		},

		setTypeOfChart : function(typeOfChart) {
			this.typeOfChart = typeOfChart;
			this.chart.updateTypeOfChart(typeOfChart);
		}
	});
});