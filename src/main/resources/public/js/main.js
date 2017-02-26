require.config({
	paths : {
		text : '../bower_components/requirejs-text/text',
		jquery : '../bower_components/jquery/jquery.min',
		underscore : '../bower_components/underscore/underscore-min',
		backbone : '../bower_components/backbone/backbone',
		templates : '../templates'
	}
});

require([ 'app' ], function(App) {
	App.initialize();
});
