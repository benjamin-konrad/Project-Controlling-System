define([ "dojo/_base/declare", "dojox/charting/Chart", "dojox/charting/axis2d/Default", "dojox/charting/plot2d/StackedAreas", "dojox/charting/themes/Wetland",
		"dojo/fx/easing", "dojo/_base/fx" ], function(declare, Chart, Default, StackedAreas, Wetland, easing, fx) {
	return declare([], {
		// Place comma-separated class attributes here. Note,
		// instance
		// attributes
		// should be initialized in the constructor. Variables
		// initialized here
		// will be treated as 'static' class variables.

		// Constructor function. Called when instance of this
		// class is
		// created
		constructor : function(idOfChart) {
			this.chart = new Chart(idOfChart);
			this.chart.addPlot("default", {
				type : StackedAreas,
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
			}).setTheme(Wetland).addSeries("Series A", [ 1, 2, 0.5, 1.5, 1, 2.8, 0.4 ]).addSeries("Series B", [ 2.6, 1.8, 2, 1, 1.4, 0.7, 2 ]).addSeries(
					"Series C", [ 6.3, 1.8, 3, 0.5, 4.4, 2.7, 2 ]).render();
			
			if(window.chartMinWidth == null)
				window.chartMinWidth = this.chart.dim.width;
			if(window.chartMinHeight == null)
				window.chartMinHeight = this.chart.dim.height;
			var _this = this;
			var startTopic = idOfChart + "Start", endTopic = idOfChart + "End";
			dojo.subscribe(startTopic, function(inst) {
				fx.fadeOut({
					node : _this.chart.domNode
				});
			});
			dojo.subscribe(endTopic, function(inst) {
				var width = inst.targetDomNode.clientWidth;
				var height = inst.targetDomNode.clientHeight;
				_this.chart.resize(width, height);
				fx.fadeIn({
					node : _this.chart.domNode,
					duration : 1000,
					"easing" : easing.bounceIn
				});
			});
		},
		
		getChart : function(){
			return this.chart;
		}
	// ,
	// Uncomment above comma and add comma-separated functions
	// here. Do
	// not leave a
	// trailing comma after last element.
	});
});