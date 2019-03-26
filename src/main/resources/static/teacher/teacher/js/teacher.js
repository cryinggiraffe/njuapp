
//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            loading: {},
            activeIndex: '1', //默认激活
            username: ''
        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);

            this.username = sessionStorage.getItem("username");
        },


        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        }

    },

    // 生命周期函数
    created() {
        
        this.loadings(); //加载动画
    },

});
