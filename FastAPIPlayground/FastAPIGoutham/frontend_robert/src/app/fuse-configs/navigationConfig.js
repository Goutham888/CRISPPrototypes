// Config file for the route fo the app

import i18next from 'i18next';
import ar from './navigation-i18n/ar';
import en from './navigation-i18n/en';
import tr from './navigation-i18n/tr';

i18next.addResourceBundle('en', 'navigation', en);
i18next.addResourceBundle('tr', 'navigation', tr);
i18next.addResourceBundle('ar', 'navigation', ar);

const navigationConfig = [
	{
		id: 'applications',
		title: 'Applications',
		translate: 'APPLICATIONS',
		type: 'group',
		icon: 'apps',
		children: [
			// Only put find and report interfaces here
			// Log in does not fit here, b/c it's not an app
			{
				id: 'find-component',
				title: 'Find',
				translate: 'FIND',
				type: 'item',
				icon: 'whatshot',
				url: '/find'
			},
			{
				id: 'report-component',
				title: 'Report',
				translate: 'REPORT',
				type: 'item',
				icon: 'whatshot',
				url: '/report'
			}
			//adding things here will make them display on the side bar
		]
	}
];

export default navigationConfig;
