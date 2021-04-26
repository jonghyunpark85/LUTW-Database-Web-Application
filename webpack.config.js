const path = require('path');
const ImageMinimizerPlugin = require('image-minimizer-webpack-plugin');

module.exports = {
  mode: 'production',
  entry: './src/main/js/app.js',
  output: {
    filename: './src/main/resources/static/built/bundle.js',
    path: __dirname,
  },
  externals: {
    jquery: 'htm',
  },
  devtool: "inline-source-map",
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.svg$/,
        use: [
          {
            loader: "raw-loader"
          }
        ]
      },
    ]
  },
};
