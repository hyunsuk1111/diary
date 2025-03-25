const { defineConfig } = require('@vue/cli-service')
const path = require('path');
module.exports = {

  publicPath: '',

  outputDir: '../main/resources/static', // 빌드경로
  devServer: {  
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    } 
  },  

  chainWebpack(config) { //빌드 시 빌드되어 나오는 js파일을 js폴더 아래로 묶어 빌드한다
    config.output.filename("js/[name].js"); 
  }, 
  
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'), // ← 여기가 중요!
      },
    },
  },
};