define([ "dojo/_base/declare", "dojo", "dojox/widget/Portlet", "dojox/layout/ResizeHandle", "dijit/layout/ContentPane", "app/portlets/ChartWidget","dojo/query" ], function(
		declare, dojo, Portlet, ResizeHandle, ContentPane, ChartWidget, query) {
	return declare([ Portlet ], {

		constructor : function() {

		},

		init : function() {
			var idOfPane = "portletContentPane" + window.id++; // +
																// window.id++;
			var idOfChart = idOfPane + window.id;
			var contentPane = new ContentPane({
				id : idOfPane,
				content : "<div id='" + idOfChart + "'></div>",
				doLayout : true
			});
			this.addChild(contentPane);
			contentPane.startup();

			new ChartWidget(idOfChart);
			var startTopic = idOfChart + "Start", endTopic = idOfChart + "End";
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
			/*var $handle = query("#"+handle.id);
			$handle[0].addClass("resizeHandleChart");*/
			this.resize(window.chartMinWidht, window.chartMinHeight);
		}
	});
});