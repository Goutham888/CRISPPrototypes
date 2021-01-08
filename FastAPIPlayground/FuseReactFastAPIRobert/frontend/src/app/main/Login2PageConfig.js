import React from 'react';

const Login2PageConfig = {
    settings: {
        layout: {
            config: {}
        }
    },
    routes: [
        {
            path: '/login',
            component: React.lazy(() => import('./Login2Page'))
        }
    ]
};

export default Login2PageConfig;
