import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CSSProperties } from '@mui/styled-engine';

interface SearchInfo {
  img: string;
  nickname: string;
  introduction : string;
  type: number;
  sx? : CSSProperties;
}

export default function SubscribeProfile(props: SearchInfo) {

  const introductionStyle: CSSProperties = {
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    whiteSpace : 'nowrap',
    width: '60vw',
    height: '2.5vh',
  };

  const { img, nickname, introduction, type, sx } = props;
  if (typeof sx === 'undefined') {
    let sx = { display: 'flex', flexDirection: 'column', width: '7rem' }
    return (
      <div style={{ margin: '0.5rem 1rem' }}>
        <Card sx={{ maxWidth: '100%', display: 'flex' }}>
          <CardMedia
            sx={sx}
            component="img"
            image={img}
            alt="profile img"
          />
          <CardContent sx={{ flex: '1 0 auto' }}>
            <Typography gutterBottom variant="h5" component="div">
              Nickname  nation
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Introduction
            </Typography>
          </CardContent>
        </Card>
      </div>
    );
  }else{
    return (
      <div style={{ margin: '0.5rem 0.5rem 0rem 0.5rem', height:'100px' }}>
        <Card sx={{ maxWidth: '100%', display: 'flex' }}>
          <CardMedia
            sx={sx} 
            component="img"
            image={img}
            alt="profile img"
          />

          <CardContent sx={{ flex: '1 0 auto' }}> 
            <Typography gutterBottom variant="body1" component="div" sx={introductionStyle} >
              {nickname}  <span>🇰🇷</span> <span>&#x1F1F0;&#x1F1F7;</span>
              
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={introductionStyle}>
             { introduction } 
            </Typography>
          </CardContent>
        </Card>
      </div>
    );
  }
}
