// The card component for the consumer side
// This will display a number of commodities on the page
// This should pop up after the consumer searches for a keyword

import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

import Card from '@material-ui/core/Card';
import Chip from '@material-ui/core/Chip';

import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
  media: {
    height: 140,
  },
});

export default function ItemCard(props) {
  const classes = useStyles();
  const item=props.item;
  const store=props.store;
  const streetAddress = props.streetAddress;
  const zipcode = props.zipcode;
  const city = props.city;
  const state = props.state;
  const time = props.time;
  const quantity = props.quantity;

  function getAddressHref() {
    var href = streetAddress.replace(" ", "+");
    href+=(",+"+city+",+"+state+",+"+zipcode);
    return href;
  }

  function getTime(){
    return new Date(time).toUTCString();
  }

  function getQuantityString(){
    var quantityString;
    switch (quantity) {
      case 1:
        quantityString = "very little";
        break;
      case 2:
        quantityString = "a medium amount of";
        break;
      case 3:
        quantityString = "a lot of";
        break;
    }
    return quantityString;
  }


  return (
    <Card className={classes.root}>
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            {item}
          </Typography>
            <Chip label={store}/>
            <Chip label={getTime()}/>
          <Typography variant="body2" color="textSecondary" component="p">
            There is {getQuantityString()} {item} at {store}
          </Typography>
        </CardContent>
      <CardActions>
        <Button size="small" color="primary" href={"https://www.google.com/maps/place/"+getAddressHref()} target="_blank">
          Show Direction
        </Button>
        <Button size="small" color="primary">
          Learn More
        </Button>
      </CardActions>
    </Card>
  );
}
