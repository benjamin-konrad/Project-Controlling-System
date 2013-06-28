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
				fident : maxType
			});

			var callbacks = dataInformations.length;
			for ( var i = 0; i < dataInformations.length; i++) {
				var dataInformation = dataInformations[i];
				var url = this.serviceUrl;
				var id = "";
				switch (filterType) {
				case "ZEIT":
					id = this._getUrlDataZeitFilter(dataInformation);
					break;
				case "ORGANISATION":
					id = this._getUrlOrganisationsFilter(restStore, dataInformation)
					break;
				case "MITARBEITER":
					id = this._getUrlMitarbeiterFilter(dataInformation);
					break;
				}
				url += id;
				url += kennzahl;
				var _this = this;
				request(url).then(function(json) {
					var obj = JSON.parse(json);
					obj.id = _this.getId(obj);
					newChartStore.put(obj);
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
			if (dataInformation.fident !== "JAHR")
				url += dataInformation.id.substring(0, 4) + "/";
			if (dataInformation.fident === "QUARTAL") {
				url += "quartal/" + dataInformation.name.substring(1, 2) + "/";
			} else if (dataInformation.fident === "MONAT") {
				url += "monat/" + dataInformation.id.substring(5, 6) + "/";
			} else
				url += dataInformation.id + "/"
			return url;
		},

		_getUrlOrganisationsFilter : function(restStore, dataInformation) {
			var url = "/organisationsfilter/";
			if (dataInformation.fident === "PROJEKT") {
				var firstIndex = dataInformation.id.indexOf("_");
				var bereich = dataInformation.id.substring(0, firstIndex);
				var projekt = dataInformation.id.substring(firstIndex + 1, dataInformation.id.length);
				url += bereich + "/" + projekt + "/";
			}
			if (dataInformation.fident === "KONTO") {
				var firstIndex = dataInformation.id.indexOf("_");
				var lastIndex = dataInformation.id.lastIndexOf("_");
				var bereich = dataInformation.id.substring(0, firstIndex);
				var projekt = dataInformation.id.substring(firstIndex + 1, lastIndex);
				var konto = dataInformation.id.substring(lastIndex + 1, dataInformation.id.length);
				url += bereich + "/" + projekt + "/" + konto + "/";
			}
			if (dataInformation.fident === "BEREICH") {
				url += dataInformation.id + "/";
			}
			return url;
		},

		_getUrlMitarbeiterFilter : function(dataInformation) {
			var url = "/mitarbeiterfilter/";
			if (dataInformation.fident === "ENTWICKLUNGSSTUFE")
				url += "estufe/";
			else
				url += "mitarbeiter/";
			return url + dataInformation.id + "/";
		},
		
		getId : function(data) {
			var name = "";
			var filter = data.filter;
			switch (filter.filterGruppe) {
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