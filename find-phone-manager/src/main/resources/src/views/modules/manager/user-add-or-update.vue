<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="飞书openAPI提供的用户唯一标识" prop="openId">
      <el-input v-model="dataForm.openId" placeholder="飞书openAPI提供的用户唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="飞书的用户名称" prop="nickName">
      <el-input v-model="dataForm.nickName" placeholder="飞书的用户名称"></el-input>
    </el-form-item>
    <el-form-item label="飞书用户的组信息(不知道这个能不能获取到，具体到最小的组信息) 隔断组使用-分割 eg：手机部-系统软件部-xxxx" prop="department">
      <el-input v-model="dataForm.department" placeholder="飞书用户的组信息(不知道这个能不能获取到，具体到最小的组信息) 隔断组使用-分割 eg：手机部-系统软件部-xxxx"></el-input>
    </el-form-item>
    <el-form-item label="头像url，根据飞书开放用户信息相关的api获取" prop="avatarUrl">
      <el-input v-model="dataForm.avatarUrl" placeholder="头像url，根据飞书开放用户信息相关的api获取"></el-input>
    </el-form-item>
    <el-form-item label="飞书用户的邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="飞书用户的邮箱"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="电话"></el-input>
    </el-form-item>
    <el-form-item label="工位(非必须)" prop="workSeat">
      <el-input v-model="dataForm.workSeat" placeholder="工位(非必须)"></el-input>
    </el-form-item>
    <el-form-item label="0:北京 1:南京 2:深圳" prop="userOrigin">
      <el-input v-model="dataForm.userOrigin" placeholder="0:北京 1:南京 2:深圳"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          openId: '',
          nickName: '',
          department: '',
          avatarUrl: '',
          email: '',
          phone: '',
          workSeat: '',
          userOrigin: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          openId: [
            { required: true, message: '飞书openAPI提供的用户唯一标识不能为空', trigger: 'blur' }
          ],
          nickName: [
            { required: true, message: '飞书的用户名称不能为空', trigger: 'blur' }
          ],
          department: [
            { required: true, message: '飞书用户的组信息(不知道这个能不能获取到，具体到最小的组信息) 隔断组使用-分割 eg：手机部-系统软件部-xxxx不能为空', trigger: 'blur' }
          ],
          avatarUrl: [
            { required: true, message: '头像url，根据飞书开放用户信息相关的api获取不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '飞书用户的邮箱不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '电话不能为空', trigger: 'blur' }
          ],
          workSeat: [
            { required: true, message: '工位(非必须)不能为空', trigger: 'blur' }
          ],
          userOrigin: [
            { required: true, message: '0:北京 1:南京 2:深圳不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/manager/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.openId = data.user.openId
                this.dataForm.nickName = data.user.nickName
                this.dataForm.department = data.user.department
                this.dataForm.avatarUrl = data.user.avatarUrl
                this.dataForm.email = data.user.email
                this.dataForm.phone = data.user.phone
                this.dataForm.workSeat = data.user.workSeat
                this.dataForm.userOrigin = data.user.userOrigin
                this.dataForm.createTime = data.user.createTime
                this.dataForm.updateTime = data.user.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/manager/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'openId': this.dataForm.openId,
                'nickName': this.dataForm.nickName,
                'department': this.dataForm.department,
                'avatarUrl': this.dataForm.avatarUrl,
                'email': this.dataForm.email,
                'phone': this.dataForm.phone,
                'workSeat': this.dataForm.workSeat,
                'userOrigin': this.dataForm.userOrigin,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
