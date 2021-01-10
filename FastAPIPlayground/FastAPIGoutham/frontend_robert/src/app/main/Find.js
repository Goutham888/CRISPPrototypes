// The find page for the CRISP app

// import DemoContent from '@fuse/core/DemoContent';
import FusePageSimple from '@fuse/core/FusePageSimple';
import {makeStyles} from '@material-ui/core/styles';
import React, {useState} from 'react';
import {useTranslation} from 'react-i18next';
import InputLabel from '@material-ui/core/InputLabel';
import ItemCard from './Components/Card'
import Select from '@material-ui/core/Select';
import FuseAnimate from '@fuse/core/FuseAnimate';
import {useForm} from '@fuse/hooks';
import Button from '@material-ui/core/Button';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Checkbox from '@material-ui/core/Checkbox';
import Divider from '@material-ui/core/Divider';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import {darken} from '@material-ui/core/styles/colorManipulator';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import clsx from 'clsx';
import {Link} from 'react-router-dom';
import * as cors from 'cors';
import MenuItem from '@material-ui/core/MenuItem';
import jwtCheck from 'app/auth/Auth'
import JwtService from 'app/services/jwtService/jwtService';

const useStyles = makeStyles(theme => ({
    layoutRoot: {}
}));

function FindPage(props) {
    const classes = useStyles(props);
    const {t} = useTranslation('findPage');
    const [results, setResults] = useState([]);
    const {form, handleChange, resetForm} = useForm({
        zipcode: '',
        item: ''
    });

    const handleSelectChange = (event) => {
        form.item=event.target.value;
      };

    function isFormValid() {
        return form.item != null;
    }


    function handleSubmit(ev) {
        ev.preventDefault();
        // added fetch code: interact with backend
        fetch("http://127.0.0.1:8000/searchItemsByZipcode/", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: new URLSearchParams(form)
        })
            .then(function (response) {
                return response.json();
            }).then(function (data) {
            setResults(data);
            console.log(data);
        })

        resetForm();

        // Jump to find page
        // window.location.replace("/report");
        // return <Redirect to="/find"/>;
    }

    return (
    <div className={clsx(classes.root, 'flex flex-col flex-auto flex-shrink-0 p-24 md:flex-row md:p-0')}>
            <FusePageSimple
                    classes={{
                        root: classes.layoutRoot
                    }}
                    header={
                        <div className="p-24 bottom-0">
                            <h1>FIND</h1>
                        </div>
                    }
                    content={
                        <div className="p-24">
                            {results.map((record, index) => (
                            <div key={index}>
                                <ItemCard item = {record.commodity} store={record.storename}/>
                                <br></br>
                            </div>
                            ))}
                        </div>

                    }
                />

            <FuseAnimate animation={{translateX: [0, '100%']}}>
                <Card className="w-full max-w-400 mx-auto m-16 md:m-0" square>
                    <CardContent className="flex flex-col items-center justify-center p-32 md:p-48 md:pt-128 ">
                        <Typography variant="h6" className="md:w-full mb-32">
                            Search for Items
                        </Typography>

                        <form
                            name="findForm"
                            noValidate
                            className="flex flex-col justify-center w-full"
                            onSubmit={handleSubmit}
                        >

                            <InputLabel id="demo-simple-select-label">Item</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select" 
                                value={form.item}
                                onChange={handleSelectChange}
                                >
                                <MenuItem value={'Water'}>Water</MenuItem>
                                <MenuItem value={'Food'}>Food</MenuItem>
                                <MenuItem value={'Batteries'}>Batteries</MenuItem>
                            </Select>
                            <br></br>
                            <TextField
                                className="mb-16"
                                label="Zipcode"
                                autoFocus
                                type="zipcode"
                                name="zipcode"
                                value={form.zipcode}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                            />

                            

                            <Button
                                variant="contained"
                                color="primary"
                                className="w-full mx-auto mt-16"
                                aria-label="SEARCH"
                                disabled={!isFormValid()}
                                type="submit"
                            >
                                SEARCH
                            </Button>
                        </form>


                    </CardContent>
                </Card>
            </FuseAnimate>
        </div>
    );
}

export default FindPage;
