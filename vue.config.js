/*
 * @Author: your name
 * @Date: 2020-08-18 13:26:12
 * @LastEditTime: 2020-08-18 14:09:36
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vue_large_screen_display\vue.config.js
 */
module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/vueDataV/" : "/",
  productionSourceMap: false,
  lintOnSave: false,
  devServer: {
    port: 8081,
    // 代理
    // proxy: {
    //   "/api": {
    //     target: "https://api.github.com",
    //     changeOrigin: true,
    //     ws: false,
    //     pathRewrite: {
    //       "^/api": ""
    //     }
    //   }
    // }
  },
  configureWebpack: {
    // 把原本需要写在webpack.config.js中的配置代码 写在这里 会自动合并
    externals: {
     'jquery' : '$',
     'echarts': 'echarts',
     'axios' : 'axios'
    }
  }
};
