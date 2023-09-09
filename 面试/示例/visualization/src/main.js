import Vue from "vue";
import App from "./App.vue";
import dataV from "@jiaminghi/data-view";
import { DatePicker } from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

Vue.use(dataV);
Vue.use(DatePicker);
Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
}).$mount("#app");
