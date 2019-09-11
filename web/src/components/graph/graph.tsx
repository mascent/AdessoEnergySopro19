import React from 'react';
import { Line } from 'react-chartjs-2';

interface graphProps {
  title: string;
  data: number[];
  dates: string[];
}
function createData(title: string, data: number[], dates: string[]) {
  return {
    labels: dates,
    datasets: [
      {
        label: title,
        fill: false,
        lineTension: 0.1,
        backgroundColor: '#f0b429',
        borderColor: '#f0b429',
        borderCapStyle: 'butt',
        borderDash: [],
        borderDashOffset: 0.0,
        borderJoinStyle: 'miter',
        pointBorderColor: '#cb6e17',
        pointBackgroundColor: '#fce588',
        pointBorderWidth: 1,
        pointHoverRadius: 5,
        pointHoverBackgroundColor: '#f0b429',
        pointHoverBorderColor: '#f0b429',
        pointHoverBorderWidth: 2,
        pointRadius: 3,
        pointHitRadius: 10,
        data
      }
    ]
  };
}

const Graph: React.FC<graphProps> = ({ data, title, dates }) => {
  return <Line data={createData(title, data, dates)} />;
};

export default Graph;
