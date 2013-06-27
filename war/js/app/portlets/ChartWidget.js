define([ "dojo/_base/declare", "dojox/charting/Chart", "dojox/charting/axis2d/Default", "dojo/fx/easing", "dojo/_base/fx", "dojox/charting/widget/SelectableLegend",
		"dojox/charting/themes/Julie", "dojox/charting/action2d/Tooltip", "dojox/charting/action2d/Highlight", "dojox/charting/action2d/MoveSlice",
		"dojox/charting/action2d/Shake" ], function(declare, Chart, Default, easing, fx, SelectableLegend, ChartTheme, Tooltip, Highlight, MoveSlice, Shake) {
	return declare([], {

		constructor : function(typeOfChart, idOfChart, idOfLegend, typeOfFilter, store) {
			this.typeOfChart = typeOfChart;
			this.idOfChart = idOfChart;
			this.idOfLegend = idOfLegend;
			this.store = store;
			this.typeOfFilter = typeOfFilter;
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

				_this.refreshTheme();
				var pieData = [];
				// this is called for each employee in the sales department
				var results = _this.store.query({})
				results.forEach(function(data) {
					if (data.value != undefined) {
						if (_this.typeOfChart !== "Pie")
							_this.chart.addSeries(_this.getName(data), [ parseFloat(data.value) ]);
						else {
							pieData.push({
								"text" : _this.getName(data),
								y : parseFloat(data.value),
								tooltip : parseFloat(data.value)
							});
						}

					}
				});

				if (_this.typeOfChart === "Pie") {
					_this.chart.addSeries(_this.typeOfFilter, pieData);
					new MoveSlice(_this.chart, "default");
				} else
					new Shake(_this.chart, "default");
				new Highlight(_this.chart, "default");
				new Tooltip(_this.chart, "default");
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
			this.updateChart();
		},
		
		updateChart : function(){
			this.chart.destroy();
			this.handlerStart.remove();
			this.handlerEnd.remove();
			this.init();
		},

		getName : function(data) {
			var name = "";
			var filter = data.filter;
			switch (this.typeOfFilter) {
			case "ZEIT":
				if (filter.jahr !== undefined)
					name += filter.jahr;
				if (filter.quartal !== undefined)
					name += "-" + filter.quartal;
				if (filter.monat !== undefined)
					name += "-" + filter.monat;
				break;
			case "ORGANISATION":
				if (filter.bereich !== undefined)
					name += filter.bereich;
				if (filter.projekt !== undefined)
					name += "-" + filter.projekt;
				if (filter.konto !== undefined)
					name += "-" + filter.konto;
				break;
			case "MITARBEITER":
				if (filter.stufe !== undefined)
					name += filter.stufe;
				if (filter.mitarbeiter !== undefined)
					name += "-" + filter.mitarbeiter;
				break;
			}
			return name;
		}
	});
});