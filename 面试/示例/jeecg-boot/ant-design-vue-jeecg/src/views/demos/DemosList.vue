<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="性别 ">
              <!--                <a-input placeholder="请输入性别 " v-model="queryParam.sex"></a-input>-->
              <j-dict-select-tag :getPopupContainer="getParentModal" type="list" v-model="queryParam.sex" dictCode="sex" placeholder="请选择性别 " />
             <!-- <a-select v-model="queryParam.sex" placeholder="请选择性别">
                <a-select-option value="">请选择</a-select-option>
                <a-select-option value="1">男</a-select-option>
                <a-select-option value="2">女</a-select-option>
              </a-select>-->
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="关键词">
              <a-input placeholder="请输入关键词" v-model="queryParam.keyWord"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="打卡时间">
                <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.punchTime_begin"></j-date>
                <span class="query-group-split-cust"></span>
                <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.punchTime_end"></j-date>
              </a-form-item>
            </a-col>

          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('demos')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <demos-modal ref="modalForm" @ok="modalFormOk"></demos-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DemosModal from './modules/DemosModal'

  export default {
    name: 'DemosList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      DemosModal
    },
    data () {
      return {
        description: 'demos管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'关键词',
            align:"center",
            dataIndex: 'keyWord'
          },
          {
            title:'打卡时间',
            align:"center",
            dataIndex: 'punchTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'工资',
            align:"center",
            dataIndex: 'salaryMoney'
          },
          {
            title:'奖金',
            align:"center",
            dataIndex: 'bonusMoney'
          },
          // {
          //   title:'性别 ',
          //   align:"center",
          //   dataIndex: 'sex_dictText'
          // },
          {
            title: '性别',
            align: "center",
            // width: 80,
            dataIndex: 'sex_dictText',
            sorter: true
          },
          {
            title:'年龄',
            align:"center",
            dataIndex: 'age'
          },
          {
            title:'生日',
            align:"center",
            dataIndex: 'birthday',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'邮箱',
            align:"center",
            dataIndex: 'email'
          },
          {
            title:'个人简介',
            align:"center",
            dataIndex: 'content'
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'所属部门编码',
            align:"center",
            dataIndex: 'sysOrgCode'
          },
          {
            title:'租户',
            align:"center",
            dataIndex: 'tenantId'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/demos/demos/list",
          delete: "/demos/demos/delete",
          deleteBatch: "/demos/demos/deleteBatch",
          exportXlsUrl: "/demos/demos/exportXls",
          importExcelUrl: "demos/demos/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'name',text:'姓名',dictCode:''})
        fieldList.push({type:'string',value:'keyWord',text:'关键词',dictCode:''})
        fieldList.push({type:'date',value:'punchTime',text:'打卡时间'})
        fieldList.push({type:'BigDecimal',value:'salaryMoney',text:'工资',dictCode:''})
        fieldList.push({type:'double',value:'bonusMoney',text:'奖金',dictCode:''})
        fieldList.push({type:'string',value:'sex',text:'性别 ',dictCode:''})
        fieldList.push({type:'int',value:'age',text:'年龄',dictCode:''})
        fieldList.push({type:'date',value:'birthday',text:'生日'})
        fieldList.push({type:'string',value:'email',text:'邮箱',dictCode:''})
        fieldList.push({type:'string',value:'content',text:'个人简介',dictCode:''})
        fieldList.push({type:'date',value:'createTime',text:'创建时间'})
        fieldList.push({type:'string',value:'sysOrgCode',text:'所属部门编码',dictCode:''})
        fieldList.push({type:'int',value:'tenantId',text:'租户',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>