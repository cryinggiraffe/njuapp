//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            checked: false,
            forget: {
                username: '',
                id_card: '',
                password1: '',
                password2: '',
            },
            flag: true,
            loading: {}, //loading动画
        };
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings(){
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
        }, 2000);
        },

        submitForm(forget) {



            this.$refs[forget].validate((valid) => {
                if (valid) {

                    var password1 = this.forget.password1
                    var password2 = this.forget.password2
                    var password = ""

                    if (password1 == password2){
                        password = password2;
                    }else{
                        window.alert("前后密码不一致")
                    }

                    this.loadings(); //加载动画

                    //提交表单
                    this.$http.post('/TeachingAssistantSystem/forget', {
                        username: this.forget.username,
                        id_card: this.forget.id_card,
                        password: password,
                    }).then(result => {
                        // 判断用户是否登录成功，后端返回JSON格式数据，不然取不到数据
                        if (result.body.success) {
                        // sessionStorage.setItem("name", this.login.name);
                        // sessionStorage.setItem("token", this.login);

                        // this.$store.dispatch("token", this.login);

                        window.location.href = "/TeachingAssistantSystem/login";
                        this.loading.close(); //关闭动画加载
                    } else {
                        // 弹出错误信息框
                        this.$emit(
                            'submit-form',
                            this.$message({
                                message: '用户名或密码错误！',
                                type: 'warning',
                                duration: 6000
                            }),
                        );
                        // 清空表单状态
                        this.$refs[forget].resetFields();
                    }
                });
                } else {
                    this.$emit(
                    'submit-form',
                    this.$message({
                        message: '输入信息有误！',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            return false;
        }
        });
        },
        loginEnter(login){
            this.submitForm(login);
        },

    }
});