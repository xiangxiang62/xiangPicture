import { defineStore } from "pinia";
import { ref } from "vue";
import {getLoginUserUsingGet} from "../api/userController";

/**
 * 存储当前登录用户的状态
 */
export const useLoginUserStore = defineStore("loginUser", () => {
  const loginUser = ref<any>({
    userName: "未登录",
  });

  /**
   * 远程获取用户登录信息
   */
  async function fetchLoginUser() {
    const res = await getLoginUserUsingGet();
    if (res.data.code === 0 && res.data.data) {
      loginUser.value = res.data.data;
    }
      // 测试用户登录，3 秒后登录
      // setTimeout(() => {
      //   loginUser.value = { userName: '测试用户', id: 1 }
      // }, 3000)

  }

  function setLoginUser(newLoginUser: any) {
    loginUser.value = newLoginUser;
  }

  return { loginUser, setLoginUser, fetchLoginUser };
});
