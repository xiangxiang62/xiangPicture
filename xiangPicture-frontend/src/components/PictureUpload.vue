<template>
  <a-upload
    v-model:file-list="fileList"
    name="avatar"
    list-type="picture-card"
    class="picture-upload"
    :show-upload-list="false"
    :custom-request="handleUpload"
    :before-upload="beforeUpload"
  >
    <img v-if="picture?.url" :src="picture?.url" alt="avatar"/>
    <div v-else>
      <loading-outlined v-if="loading"></loading-outlined>
      <plus-outlined v-else></plus-outlined>
      <div class="ant-upload-text">点击或拖拽上传图片</div>
    </div>
  </a-upload>
</template>
<script lang="ts" setup>
import {ref} from 'vue';
import {PlusOutlined, LoadingOutlined} from '@ant-design/icons-vue';
import {message} from 'ant-design-vue';
import type {UploadChangeParam, UploadProps} from 'ant-design-vue';
import {updatePictureUsingPost, uploadPictureUsingPost} from "../api/pictureController";

interface Props {
  picture?: API.PictureVO
  onSuccess?: (newPicture: API.PictureVO) => void
}

const props = defineProps<Props>()

/**
 * 上传图片
 **/
const handleUpload = async ({file}: any) => {
  loading.value = true;
  try {
    const params = props.picture ? {id:props.picture.id} :{};
    const res = await uploadPictureUsingPost(params,{},file,{});
    if (res.data.code === 0 && res.data.data) {
      message.success("上传成功！")
      props.onSuccess?.(res.data.data);
    } else {
      message.error("上传失败！" + res.data.message);
    }
  }catch (error){
    message.error("上传失败！" + error.message);
  }
  loading.value = false;
}

function getBase64(img: Blob, callback: (base64Url: string) => void) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result as string));
  reader.readAsDataURL(img);
}

const fileList = ref([]);
const loading = ref<boolean>(false);

const handleChange = (info: UploadChangeParam) => {
  if (info.file.status === 'uploading') {
    loading.value = true;
    return;
  }
  if (info.file.status === 'done') {
    // Get this url from response in real world.
    getBase64(info.file.originFileObj, (base64Url: string) => {
      imageUrl.value = base64Url;
      loading.value = false;
    });
  }
  if (info.file.status === 'error') {
    loading.value = false;
    message.error('upload error');
  }
};

/**
 * 文件上传之前的校验
 * @param file
 */
const beforeUpload = (file: UploadProps['fileList'][number]) => {
  // 校验图片格式
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJpgOrPng) {
    message.error('只能上传 jpg 或者 png 格式的图片');
  }
  // 校验图片大小
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('图片最大支持上传 2MB 大小');
  }
  return isJpgOrPng && isLt2M;
};
</script>
<style scoped>
.picture-upload :deep(.ant-upload) {
  width: 100% !important;
  height: 100% !important;
  min-width: 152px;
  min-height: 152px;
}

.picture-upload img {
max-width: 100%;
  max-height: 480px;
}


.avatar-uploader > .ant-upload {
  width: 128px;
  height: 128px;
}

.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
