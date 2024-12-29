<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="200px">
        <RouterLink to="/">
          <div class="title-bar">
            <img class="logo" src="../assets/logo.png" alt="logo"/>
            <div class="title">享 · 云图库</div>
          </div>
        </RouterLink>
      </a-col>
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="current"
          mode="horizontal"
          :items="items"
          @Click="doMenuClick"
        />
      </a-col>
      <a-col flex="120px">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            {{ loginUserStore.loginUser.userName ?? '无名' }}
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>

      </a-col>
    </a-row>

  </div>
</template>
<script lang="ts" setup>
import {h, ref} from 'vue'
import {HomeOutlined} from '@ant-design/icons-vue'
import {MenuProps} from 'ant-design-vue'
import {useRouter} from "vue-router";
import {useLoginUserStore} from "../stores/useLoginUserStore";
const loginUserStore = useLoginUserStore()
const router = useRouter();

const items = ref<MenuProps['items']>([
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/about',
    label: '关于',
    title: '关于',
  },
  {
    key: 'others',
    label: h('a', {href: 'https://www.codefather.cn', target: '_blank'}, '编程导航'),
    title: '编程导航',
  },
])


// 点击菜单项的时候进行页面的跳转
const doMenuClick = ({key}) => {
  router.push(key)
}

// 指定当前导航栏应该高亮哪个
const current = ref<string[]>([])
// 监听路由变化，更新高里昂菜单项
router.afterEach((to,from,next) => {
  current.value = [to.path]
})


</script>


<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  font-size: 18px;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>


