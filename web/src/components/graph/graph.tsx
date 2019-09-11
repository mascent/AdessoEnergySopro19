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
        backgroundColor: 'rgba(75,192,192,0.4)',
        borderColor: 'rgba(75,192,192,1)',
        borderCapStyle: 'butt',
        borderDash: [],
        borderDashOffset: 0.0,
        borderJoinStyle: 'miter',
        pointBorderColor: 'rgba(75,192,192,1)',
        pointBackgroundColor: '#fff',
        pointBorderWidth: 1,
        pointHoverRadius: 5,
        pointHoverBackgroundColor: 'rgba(75,192,192,1)',
        pointHoverBorderColor: 'rgba(220,220,220,1)',
        pointHoverBorderWidth: 2,
        pointRadius: 1,
        pointHitRadius: 10,
        data
      }
    ]
  };
}

const Graph: React.FC<graphProps> = ({ data, title, dates }) => {
  return (
    <div>
      <h2>Line Example</h2>
      <Line data={createData(title, data, dates)} />
    </div>
  );
};

export default Graph;
