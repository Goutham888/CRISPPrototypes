import DemoContent from '@fuse/core/DemoContent';
import FusePageSimple from '@fuse/core/FusePageSimple';
import FusePageCarded from '@fuse/core/FusePageCarded'
import {makeStyles} from '@material-ui/core/styles';
import React, {useRef, useState} from 'react';
import {useTranslation} from 'react-i18next';

import {
    SelectFormsy,
    TextFieldFormsy
} from '@fuse/core/formsy';
import Button from '@material-ui/core/Button';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Radio from '@material-ui/core/Radio';
import Typography from '@material-ui/core/Typography';
import Formsy from 'formsy-react';

const useStyles = makeStyles(theme => ({
    layoutRoot: {}
}));
const suggestions = ['Sea', 'Sky', 'Forest', 'Aerial', 'Art'].map(item => ({
    value: item,
    label: item
}));

function ReportPage(props) {
    const classes = useStyles(props);
    const {t} = useTranslation('reportPage');

    const [isFormValid, setIsFormValid] = useState(false);
    const formRef = useRef(null);

    function disableButton() {
        setIsFormValid(false);
    }

    function enableButton() {
        setIsFormValid(true);
    }

    function handleSubmit(model) {

        const token = localStorage.getItem('jwt_access_token')
        let now = new Date().toISOString();
        model["time"] = now;
        // console.info('submit', model);
        fetch("http://127.0.0.1:8000/users/me/items/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(model)

        })
        // alert(token)
        console.log(JSON.stringify(model))
    }

    return (
        <FusePageCarded
            classes={{
                root: classes.layoutRoot
            }}
            header={
                <div className="p-24 bottom-0">
                    <h1>Report an Item!</h1>
                </div>
            }
            // contentToolbar={
            // 	<div className="px-24">
            // 		<h4>Report Items</h4>
            // 	</div>
            // }
            content={
                <div className="p-24">
                    <Formsy
                        onValidSubmit={handleSubmit}
                        onValid={enableButton}
                        onInvalid={disableButton}
                        ref={formRef}
                        className="flex flex-col justify-center"
                    >

                        <SelectFormsy
                            className="my-16"
                            name="commodity"
                            label="Item name"
                            value="cookies"
                        >
                            <MenuItem value="cookies">Cookies</MenuItem>
                            <MenuItem value="paper">Paper</MenuItem>
                            <MenuItem value="bulbs">Light bulbs</MenuItem>
                        </SelectFormsy>

                        <SelectFormsy
                            className="my-16"
                            name="store_id"
                            label="Store"
                            value={1}
                        >
                            <MenuItem value={1}>Kroger</MenuItem>
                            <MenuItem value={2}>Harris Teeter</MenuItem>
                            <MenuItem value={3}>Foods of All Nations</MenuItem>
                        </SelectFormsy>

                        <SelectFormsy
                            className="my-16"
                            name="quantity"
                            label="Quantity"
                            value="0"
                        >
                            <MenuItem value={0}>None</MenuItem>
                            <MenuItem value={1}>A few</MenuItem>
                            <MenuItem value={2}>Many</MenuItem>
                        </SelectFormsy>


                        <TextFieldFormsy
                            className="mb-16"
                            type="text"
                            name="description"
                            label="Note"
                        />

                        <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                            className="mx-auto mt-32 mb-80"
                            aria-label="LOG IN"
                            disabled={!isFormValid}
                        >
                            submit
                        </Button>
                    </Formsy>
                </div>
            }
        />
    );
}

export default ReportPage;
