define([ "dojo/_base/declare", "dojox/charting/Chart", "dojox/charting/axis2d/Default", "dojo/fx/easing", "dojo/_base/fx",
		"dojox/charting/widget/SelectableLegend", "dojox/charting/themes/Julie" ], function(declare, Chart, Default, easing, fx, SelectableLegend, ChartTheme) {
	return declare([], {

		constructor : function(typeOfChart, idOfChart, idOfLegend, store) {
			this.typeOfChart = typeOfChart;
			this.idOfChart = idOfChart;
			this.idOfLegend = idOfLegend;
			this.store = store;
			this.init();
		},

		init : function() {
			var _this = this;
			require([ "dojox/charting/plot2d/" + this.typeOfChart ], function(chartType) {
				_this.chart = new Chart(_this.idOfChart);
				_this.chart.addPlot("default", {
					type : chartType,
					tension : 3,
					animate : {
						duration : 200,
						easing : easing.bounceInOut
					}
				}).addAxis("x", {
					fixLower : "major",
					fixUpper : "major"
				}).addAxis("y", {
					vertical : true,
					fixLower : "major",
					fixUpper : "major",
					min : 0
				}).setTheme(ChartTheme);

				// this is called for each employee in the sales department
				_this.store.query({
					"type" : "month"
				}).forEach(function(data) {
					_this.chart.addSeries(data.parent + "-" + data.name, [ data.value ]);
				});

				_this.chart.render();

				if (_this.legend === undefined) {
					_this.legend = new SelectableLegend({
						chart : _this.chart,
						horizontal : true
					}, _this.idOfLegend);
					_this.legend.startup();
				} else
					_this.legend.refresh();

				if (window.chartMinWidth == null)
					window.chartMinWidth = _this.chart.dim.width;
				if (window.chartMinHeight == null)
					window.chartMinHeight = _this.chart.dim.height;

				var startTopic = _this.idOfChart + "Start", endTopic = _this.idOfChart + "End";

				_this.handlerStart = dojo.subscribe(startTopic, function(inst) {
					fx.fadeOut({
						node : _this.chart.domNode
					});
				});

				_this.handlerEnd = dojo.subscribe(endTopic, function(inst) {
					var width = inst.targetDomNode.clientWidth;
					var height = inst.targetDomNode.clientHeight;
					_this.chart.resize(width, height);
					fx.fadeIn({
						node : _this.chart.domNode,
						duration : 1000,
						"easing" : easing.bounceIn
					});
				});
			});
		},

		refreshTheme : function() {
			var _this = this;
			require([ "dojox/charting/themes/" + window.chartTheme ], function(theme) {
				_this.chart.setTheme(theme).render();
			});
		},

		getChart : function() {
			return this.chart;
		},

		updateTypeOfChart : function(typeOfChart) {
			this.typeOfChart = typeOfChart;
			this.chart.destroy();
			this.handlerStart.remove();
			this.handlerEnd.remove();
			this.init();
		}
	});
});