define([ "dojo/_base/declare", "dojo", "dojo/store/Memory", "dojo/store/Observable", "dojo/request" ], function(declare, dojo, Memory, Observable, request) {
	return declare([], {

		constructor : function(serviceUrl) {
			this.serviceUrl = serviceUrl;
		},

		fetchData : function(filterType, kennzahl, dndStore, restStore, callback, chartStore) {
			var newChartStore = chartStore;
			if (newChartStore === undefined) {
				var store = new Memory({
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

				newChartStore = new Observable(store);
			}
			var maxType = dndStore.query({
				id : "maxType"
			})[0].maxType;

			var dataInformations = dndStore.query({
				type : maxType
			});

			var callbacks = dataInformations.length;
			for ( var i = 0; i < dataInformations.length; i++) {
				var dataInformation = dataInformations[i];
				var url = this.serviceUrl;
				switch (filterType) {
				case "ZEIT":
					url += this._getUrlDataZeitFilter(dataInformation);
					break;
				case "ORGANISATION":
					url += this._getUrlOrganisationsFilter(restStore, dataInformation)
					break;
				case "MITARBEITER":
					url += this._getUrlMitarbeiterFilter(dataInformation);
					break;
				}
				url += kennzahl
				request(url).then(function(json) {
					var obj = JSON.parse(json);
					newChartStore.add(obj);
					callbacks--;
					if (callbacks === 0) {
						callback(newChartStore);
					}
				}, function(error) {
					console.log("error: " + error);
				});
			}
		},

		_getUrlDataZeitFilter : function(dataInformation) {
			var url = "/zeitfilter/";
			if (dataInformation.type !== "JAHR")
				url += dataInformation.id.substring(0,4) + "/";
			if (dataInformation.fident === "QUARTAL") {
				url += "quartal/" + dataInformation.name.substring(1, 1) + "/";
			} else if (dataInformation.fident === "MONAT") {
				url += "monat/" + dataInformation.id.substring(5, 5) + "/";
			} else
				url += dataInformation.id + "/"
			return url;
		},

		_getUrlOrganisationsFilter : function(restStore, dataInformation) {
			var url = "/organisationsfilter/";
			if (dataInformation.fident === "KONTO") {
				var foundParent = dndStore.query({
					id : dataInformation.oldParent
				});
				url += foundParent[0].parent + "/";
			}
			if (dataInformation.fident !== "BEREICH")
				url += dataInformation.oldParent + "/";

			return url += dataInformation.id + "/";
		},

		_getUrlMitarbeiterFilter : function(dataInformation) {
			var url = "/mitarbeiterfilter/";
			if (dataInformation.fident === "ENTWICKLUNGSSTUFE")
				url += "estuf/";
			else
				url += "mitarbeiter/";
			return url + dataInformation.id + "/";
		},

	});
});