import i18next from 'i18next';
import Find from './Find';
import Report from './Report';
import Login from './Login2Page'
import Register from './Register2Page'

import en from './i18n/en';
import tr from './i18n/tr';
import ar from './i18n/ar';

i18next.addResourceBundle('en', 'examplePage', en);
i18next.addResourceBundle('tr', 'examplePage', tr);
i18next.addResourceBundle('ar', 'examplePage', ar);

const ExampleConfig = {
	settings: {
		layout: {
			config: {}
		}
	},

	// to add new routes, add it HERE!
	// Not the routesConfig.js
	routes: [
		{
			path: '/find',
			component: Find,
		},
		{
			path: '/report',
			component: Report,
		},
		{
			path: '/login',
			component: Login,
		},
		{
			path: '/register',
			component: Register,
		},
	]
};

export default ExampleConfig;

/**
 * Lazy load Example
 */
/*
import React from 'react';

const ExampleConfig = {
    settings: {
        layout: {
            config: {}
        }
    },
    routes  : [
        {
            path     : '/example',
            component: React.lazy(() => import('./Example'))
        }
    ]
};

export default ExampleConfig;

*/
