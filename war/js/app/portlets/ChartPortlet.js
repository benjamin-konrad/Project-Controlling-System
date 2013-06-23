define([ "dojo/_base/declare", "dojo", "dojox/widget/Portlet", "dojox/layout/ResizeHandle", "dijit/layout/ContentPane", "app/portlets/ChartWidget", "dojo/_base/lang",
         "dojo/dom-construct" ], function(declare, dojo, Portlet, ResizeHandle, ContentPane, ChartWidget, lang, domConstruct) {
	return declare([ Portlet ], {

		constructor : function() {

		},

		doPostCreate : function() {
			this.idOfPane = "portletContentPane" + window.id++; // +
			this.idOfStandby = this.idOfPane + "Standby";
			this.idOfChart = this.idOfPane + window.id;
			this.idOfLegend = this.idOfChart + "Legend";
			
			var contentPane = new ContentPane({
				id : this.idOfPane,
				content : '<div id="' + this.idOfStandby +'" style="text-align:center"><img src="img/ajax-loader.gif" width="64" height="64" alt="" /><br/><br/>Statistikinformationen werden geladen...</div>' + "<div id='" + this.idOfChart + "'></div>",
				doLayout : true
			});
			this.addChild(contentPane);
			contentPane.startup();
			
			contentPane = new ContentPane({
				content : "<div id='" + this.idOfLegend + "'></div>",
				doLayout : true
			});
			this.addChild(contentPane);
			contentPane.startup();
		},

		init : function(typeOfFilter, typeOfKennzahl, typeOfChart, editHandler, dataStore) {
			this._createIcon("dojoxPortletSettingsIcon", "dojoxPortletSettingsIconHover", lang.hitch(this, this._onClickEdit));
			this.editHandler = editHandler;
			this.typeOfFilter = typeOfFilter;
			this.typeOfKennzahl = typeOfKennzahl;
			this.typeOfChart = typeOfChart;
			this.widgetStore = widgetStore;
			domConstruct.destroy(this.idOfStandby);
			
			
			this.chart = new ChartWidget(typeOfChart, this.idOfChart, this.idOfLegend, widgetStore);
					
			var startTopic = this.idOfChart + "Start";
			var endTopic = this.idOfChart + "End";

			var handle = new dojox.layout.ResizeHandle({
				targetId : this.idOfPane,
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
		
		getDataStore : function(){
			return this.chart.getDataStore();
		},
		
		refreshChart : function(store){
			this.chart.updateDataStore(store);
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