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

export default function ItemCard() {
  const classes = useStyles();

  return (
    <Card className={classes.root}>
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            Cookies
          </Typography>
            {/*<Chip color="primary" label="Kroger" />*/}
            <Chip label="Kroger"/>
            <Chip label="12/11 3:30 pm"/>
          <Typography variant="body2" color="textSecondary" component="p">
            As of 12/11 3:30 pm, there are lots of cookies at Kroger
          </Typography>
        </CardContent>
      <CardActions>
        <Button size="small" color="primary">
          Show Direction
        </Button>
        <Button size="small" color="primary">
          Learn More
        </Button>
      </CardActions>
    </Card>
  );
}
