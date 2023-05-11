<template>
    <div>
        #[[$END$]]#
    </div>

</template>

<script>

//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import <组件名称> from '《组件路径》'

export default {
    name: "${COMPONENT_NAME}",
    //import引入的组件需要注入到对象中才能使用
    components: {},
    data() {
        //这里存放数据",
        return {};
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {},
    //生命周期 - 创建之前 -- vue实例中的el，data，data中的message都为undefined
    beforeCreate() {
    },
    //生命周期 - 创建完成（可以访问当前this实例） , el还是undefined，而数据已经与data中的属性进行绑定
    created() {
    },
    //生命周期 - 挂载之前 - 载入前（完成了data和el数据初始化），但是页面中的内容还是vue中的占位符
    beforeMount() {
    },
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
    },
    //生命周期 - 更新之前 -- view层的数据变化前，不是data中的数据改变前
    beforeUpdate() {
    },
    //生命周期 - 更新之后
    updated() {
    },
    //生命周期 - 销毁之前
    beforeDestroy() {
    },
    //生命周期 - 销毁完成
    destroyed() {
    },
    //如果页面有keep-alive缓存功能，这个函数会触发
    activated() {
    },
}
</script>

<style scoped>

</style>