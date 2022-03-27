<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="手机品牌" prop="brand">
      <el-input v-model="dataForm.brand" placeholder="手机品牌"></el-input>
    </el-form-item>
    <el-form-item label="0:北京 1:南京 2:深圳" prop="phoneOrigin">
      <el-input v-model="dataForm.phoneOrigin" placeholder="0:北京 1:南京 2:深圳"></el-input>
    </el-form-item>
    <el-form-item label="镭雕号" prop="laserNumer">
      <el-input v-model="dataForm.laserNumer" placeholder="镭雕号"></el-input>
    </el-form-item>
    <el-form-item label="手机类型" prop="phoneType">
      <el-input v-model="dataForm.phoneType" placeholder="手机类型"></el-input>
    </el-form-item>
    <el-form-item label="版本号: 0:国内、1:国际、2:印度" prop="phoneVersion">
      <el-input v-model="dataForm.phoneVersion" placeholder="版本号: 0:国内、1:国际、2:印度"></el-input>
    </el-form-item>
    <el-form-item label="持有者ID" prop="ownerUserId">
      <el-input v-model="dataForm.ownerUserId" placeholder="持有者ID"></el-input>
    </el-form-item>
    <el-form-item label="内存" prop="memory">
      <el-input v-model="dataForm.memory" placeholder="内存"></el-input>
    </el-form-item>
    <el-form-item label="硬盘" prop="disk">
      <el-input v-model="dataForm.disk" placeholder="硬盘"></el-input>
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
          brand: '',
          phoneOrigin: '',
          laserNumer: '',
          phoneType: '',
          phoneVersion: '',
          ownerUserId: '',
          memory: '',
          disk: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          brand: [
            { required: true, message: '手机品牌不能为空', trigger: 'blur' }
          ],
          phoneOrigin: [
            { required: true, message: '0:北京 1:南京 2:深圳不能为空', trigger: 'blur' }
          ],
          laserNumer: [
            { required: true, message: '镭雕号不能为空', trigger: 'blur' }
          ],
          phoneType: [
            { required: true, message: '手机类型不能为空', trigger: 'blur' }
          ],
          phoneVersion: [
            { required: true, message: '版本号: 0:国内、1:国际、2:印度不能为空', trigger: 'blur' }
          ],
          ownerUserId: [
            { required: true, message: '持有者ID不能为空', trigger: 'blur' }
          ],
          memory: [
            { required: true, message: '内存不能为空', trigger: 'blur' }
          ],
          disk: [
            { required: true, message: '硬盘不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/phone/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.brand = data.phone.brand
                this.dataForm.phoneOrigin = data.phone.phoneOrigin
                this.dataForm.laserNumer = data.phone.laserNumer
                this.dataForm.phoneType = data.phone.phoneType
                this.dataForm.phoneVersion = data.phone.phoneVersion
                this.dataForm.ownerUserId = data.phone.ownerUserId
                this.dataForm.memory = data.phone.memory
                this.dataForm.disk = data.phone.disk
                this.dataForm.createTime = data.phone.createTime
                this.dataForm.updateTime = data.phone.updateTime
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
              url: this.$http.adornUrl(`/manager/phone/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'brand': this.dataForm.brand,
                'phoneOrigin': this.dataForm.phoneOrigin,
                'laserNumer': this.dataForm.laserNumer,
                'phoneType': this.dataForm.phoneType,
                'phoneVersion': this.dataForm.phoneVersion,
                'ownerUserId': this.dataForm.ownerUserId,
                'memory': this.dataForm.memory,
                'disk': this.dataForm.disk,
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
