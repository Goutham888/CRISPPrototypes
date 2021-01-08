import React from 'react';

const Register2PageConfig = {
	settings: {
		layout: {
			config: {}
		}
	},
	routes: [
		{
			path: '/register',
			component: React.lazy(() => import('./Register2Page'))
		}
	]
};

export default Register2PageConfig;
