// The find page for the CRISP app

// import DemoContent from '@fuse/core/DemoContent';
import FusePageSimple from '@fuse/core/FusePageSimple';
import {makeStyles} from '@material-ui/core/styles';
import React from 'react';
import {useTranslation} from 'react-i18next';

import ItemCard from './Components/Card'

const useStyles = makeStyles(theme => ({
    layoutRoot: {}
}));

function FindPage(props) {
    const classes = useStyles(props);
    const {t} = useTranslation('findPage');

    return (
        <FusePageSimple
            classes={{
                root: classes.layoutRoot
            }}
            header={
                <div className="p-24 bottom-0">
                    <h1>CRISP APP</h1>
                </div>
            }
            content={
                <div className="p-24">
                    <ItemCard/>
                </div>

            }
        />
    );
}

export default FindPage;
