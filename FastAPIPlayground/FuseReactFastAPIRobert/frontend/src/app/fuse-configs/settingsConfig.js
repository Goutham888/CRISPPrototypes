// The visual setting for the entire app
// theme AND the layout (I used 1)
const settingsConfig = {
	layout: {
		style: 'layout1', // layout-1 layout-2 layout-3
		config: {} // checkout default layout configs at app/fuse-layouts for example  app/fuse-layouts/layout1/Layout1Config.js
	},
	customScrollbars: false,
	animations: true,
	direction: 'ltr', // rtl, ltr
	theme: {
		main: 'light8',
		navbar: 'light8',
		toolbar: 'light8',
		footer: 'light8'
	}
};

export default settingsConfig;
