const BugsnagSourceMapPlugin = require('bugsnag-sourcemap-webpack-plugin');
const Glob = require('glob');
const Path = require('path');

let entries = {};

// const dir = Path.join(__dirname, "src/main/ts");
//
// Glob.sync(`${dir}/*.ts*`).forEach(function(file) {
//   entries[file.substring(file.lastIndexOf('/')+1, file.length).replace(/\.tsx?$/, "")] = file
// });

Glob.sync("./src/main/ts/*.ts*").forEach(function(file) {
  entries[file.substring(file.lastIndexOf('/')+1, file.length).replace(/\.tsx?$/, "")] = file
});

let moduleDefinition = {
  entry: entries,
  output: {
    path: Path.join(__dirname, 'src/main/resources/static/js'),
    filename: '[name].js'
  },
  devtool: "cheap-module-source-map",
  plugins: [],
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.d.ts']
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        exclude: /target/,
        use: [
          {
            loader: 'ts-loader'
          }
        ]
      }
    ],
  },
  mode: 'development',
};

switch (process.env.NODE_ENV) {
  // case 'staging':
  //   moduleDefinition['plugins'].push(new BugsnagSourceMapPlugin({
  //     apiKey: '93fc6dd2005f1fabdf95f1b9b307c6f7',
  //     publicPath: '*smoosy-staging.atlas.jp*',
  //     overwrite: true,
  //     uploadSource: false,
  //     removeSourceMap: true
  //   }));
  //   moduleDefinition.devtool = 'source-map';
  //   break;
  // case 'pre':
  //   moduleDefinition['plugins'].push(new BugsnagSourceMapPlugin({
  //     apiKey: '93fc6dd2005f1fabdf95f1b9b307c6f7',
  //     publicPath: '*smoosy-pre.atlas.jp*',
  //     overwrite: true,
  //     uploadSource: true,
  //     removeSourceMap: true
  //   }));
  //   moduleDefinition.devtool = 'source-map';
  //   moduleDefinition.mode = 'production';
  //   break;
  // case 'production':
  //   moduleDefinition['plugins'].push(new BugsnagSourceMapPlugin({
  //     apiKey: '93fc6dd2005f1fabdf95f1b9b307c6f7',
  //     publicPath: '*smoosy.atlas.jp*',
  //     overwrite: true,
  //     uploadSource: true,
  //     removeSourceMap: true
  //   }));
  //   moduleDefinition.devtool = 'source-map';
  //   moduleDefinition.mode = 'production';
  //   break;
  default:
    break;
}

module.exports = moduleDefinition;
