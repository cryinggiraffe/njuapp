/**
 * 校外客户信息列表
 */

//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            //element-ui的table需要的参数必须是Array类型的
            videos: [{
                id: '',
                vId: '',
                vName: '',
                url: '',
                count: 0,
                uploadTime: ''
            }],

            //编辑表
            editor: {
                vId: '',
                vName: ''
            },

            count: 0,
            username: '',
            cId: '',


            multipleSelection: [],
            selectIds: [],
            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,

            loading: {},

            //文件上传的参数
            dialogImageUrl: '',
            dialogVisible: false,
            //图片列表（用于回显图片）
            fileList: [{name: '', url: ''}],

            activeIndex: '2', //默认激活
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
            this.cId = sessionStorage.getItem("cId");
        },

        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },

        //刷新列表
        reloadList() {
            this.findAllVideos();
        },

        findAllVideos() {
            this.loadings();
            this.$http.post('/TeachingAssistantSystem/video/findAllVideos',{
                cId: this.cId
            }).then(result => {
                console.log(result);
            this.videos = result.data;
            //this.pageConf.totalPage = result.body.total;
            this.loading.close(); //数据更新成功就手动关闭动画
        });
        },

        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.videos.clearSelection();
        },
        //更新按钮（表格）
        handleEdit(id) {
            //打开dialog
            this.showEditor = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.post('/TeachingAssistantSystem/video/findByVId', {vId: id}).then(result => {
                //this.editor = result.body[0];
                console.log(result);
            console.log(result.data);

            this.editor = result.data;
            //移除element-ui表单校验残留
            this.$refs['editor'].resetFields();

            console.log(this.editor)
        });
        },

        //更新
        sureEdit(editor) {
            //关闭对话框
            this.showEditor = false;

            console.log(this.editor)
            //调用更新数据的接口
            this.$http.post('/TeachingAssistantSystem/video/update', {
                vId: this.editor.vId,
                vName: this.editor.vName
            }).then(result => {
                if (result.body.success) {
                //更新成功
                this.$message({
                    type: 'success',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
                this.goods = [];
                this.$refs.editor.resetFields();
            } else {
                //更新失败
                this.$message({
                    type: 'warning',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
                this.$refs.editor.resetFields();
            }
        })
        },

        handleDelete(id) {
            var ids = new Array();
            //var ids = {};
            //ids.id = id;
            //var ids = "";
            //ids = id;
            ids.push(id);
            //var ids = id + "";
            console.log(ids);
            this.sureDelete(ids[0]);
        },

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post('/TeachingAssistantSystem/video/delete',{
                vId: ids
            }).then(result => {
                if (result.body.success) {
                //删除成功
                this.selectIds = []; //清空选项
                this.$message({
                    type: 'success',
                    message: result.body.message,
                    duration: 6000
                });
                this.reloadList();
            } else {
                //删除失败
                this.selectIds = []; //清空选项
                this.$message({
                    type: 'warning',
                    message: result.body.message,
                    duration: 6000
                });
                //刷新列表
                this.reloadList();
            }
        });
        }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消删除',
                duration: 6000
            });
        });
        },

    },

    // 生命周期函数
    created() {
        this.findAllVideos();
        this.loadings(); //加载动画
    },

});
