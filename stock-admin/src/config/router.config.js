// eslint-disable-next-line
import { UserLayout, BasicLayout, BlankLayout } from '@/layouts'
import { bxAnaalyse } from '@/core/icons'

const RouteView = {
  name: 'RouteView',
  render: h => h('router-view')
}

export const asyncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BasicLayout,
    meta: { title: 'menu.home' },
    redirect: '/dashboard/workplace',
    children: [
      // dashboard
      {
        path: '/dashboard',
        name: 'dashboard',
        redirect: '/dashboard/workplace',
        component: RouteView,
        meta: { title: 'menu.dashboard', keepAlive: true, icon: bxAnaalyse, permission: ['dashboard'] },
        children: [
          // {
          //   path: '/dashboard/analysis/:pageNo([1-9]\\d*)?',
          //   name: 'Analysis',
          //   component: () => import('@/views/dashboard/Analysis'),
          //   meta: { title: 'menu.dashboard.analysis', keepAlive: false, permission: ['dashboard'] }
          // },
          // 外部链接
          // {
          //   path: 'https://www.baidu.com/',
          //   name: 'Monitor',
          //   meta: { title: 'menu.dashboard.monitor', target: '_blank' }
          // },
          {
            path: '/dashboard/workplace',
            name: 'Workplace',
            component: () => import('@/views/dashboard/Workplace'),
            meta: { title: 'menu.dashboard.workplace', keepAlive: true, permission: ['dashboard'] }
          }
        ]
      },
      {
        path: '/userlist',
        redirect: '/userlist/index',
        component: RouteView,
        meta: { title: '用户管理', icon: 'usergroup-delete', permission: ['userlist'] },
        children: [
          {
            path: '/userlist/index',
            name: 'Userlist',
            component: () => import('@/views/userlist/index'),
            meta: { title: '用户列表', keepAlive: true, permission: ['userlist'] }
          },
          {
            path: '/userlist/agentlist',
            name: 'Agentlist',
            component: () => import('@/views/userlist/agentlist'),
            meta: { title: '代理列表', keepAlive: true, permission: ['agentlist'] }
          }
        ]
      },
      // {
      //   path: '/allotment',
      //   redirect: '/allotment/allotmentlist',
      //   component: RouteView,
      //   meta: { title: '分仓配资', icon: 'apartment', permission: ['allotment'] },
      //   children: [
      //     {
      //       path: '/allotment/allotmentlist',
      //       name: 'allotment',
      //       component: () => import('@/views/allotment/allotmentlist'),
      //       meta: { title: '配资列表', keepAlive: true, permission: ['allotment'] }
      //     },
      //     {
      //       path: '/allotment/leversetting',
      //       name: 'leversetting',
      //       component: () => import('@/views/allotment/leversetting'),
      //       meta: { title: '杠杆配置', keepAlive: true, permission: ['leversetting'] }
      //     },
      //     {
      //       path: '/allotment/brokermechanism',
      //       name: 'brokermechanism',
      //       component: () => import('@/views/allotment/brokerMechanism'),
      //       meta: { title: '券商机构管理', keepAlive: true, permission: ['brokermechanism'] }
      //     },
      //     {
      //       path: '/allotment/securities',
      //       name: 'securities',
      //       component: () => import('@/views/allotment/securities'),
      //       meta: { title: '证券信息管理', keepAlive: true, permission: ['securities'] }
      //     },
      //     {
      //       path: '/allotment/transactionAccount',
      //       name: 'transactionAccount',
      //       component: () => import('@/views/allotment/transactionAccount'),
      //       meta: { title: '交易账户管理', keepAlive: true, permission: ['transactionAccount'] }
      //     },
      //     {
      //       path: '/allotment/expand',
      //       name: 'expand',
      //       component: () => import('@/views/allotment/expand'),
      //       meta: { title: '配资资金管理', keepAlive: true, permission: ['expand'] }
      //     },
      //     {
      //       path: '/allotment/allotmentsetting',
      //       name: 'allotmentsetting',
      //       component: () => import('@/views/allotment/allotmentSetting'),
      //       meta: { title: '配资设置', keepAlive: true, permission: ['allotmentsetting'] }
      //     },
      //   ]
      // },
      // 产品管理
      {
        path: '/product',
        redirect: '/product/shares',
        component: RouteView,
        meta: { title: '产品管理', icon: 'area-chart', permission: ['shares'] },
        children: [
          {
            path: '/product/shares',
            name: 'shares',
            component: () => import('@/views/product/shares'),
            meta: { title: '股票产品', keepAlive: true, permission: ['shares'] }
          },
          {
            path: '/product/index',
            name: 'index',
            component: () => import('@/views/product/index'),
            meta: { title: '指数产品', keepAlive: true, permission: ['index'] }
          }
          // {
          //   path: '/product/basecurrency',
          //   name: 'basecurrency',
          //   component: () => import('@/views/product/baseCurrency'),
          //   meta: { title: '基础货币', keepAlive: true, permission: ['basecurrency'] }
          // },
          // {
          //   path: '/product/futures',
          //   name: 'futures',
          //   component: () => import('@/views/product/futures'),
          //   meta: { title: '期货产品', keepAlive: true, permission: ['futures'] }
          // },
        ]
      },
      // 持仓管理
      {
        path: '/position',
        redirect: '/position/financing',
        component: RouteView,
        meta: { title: '持仓管理', icon: 'money-collect', permission: ['financing'] },
        children: [
          {
            path: '/position/financing',
            name: 'financing',
            component: () => import('@/views/position/financing'),
            meta: { title: '持仓管理', keepAlive: true, permission: ['financing'] }
          },
          {
            path: '/position/createfinancing',
            name: 'createfinancing',
            component: () => import('@/views/position/createFinancing'),
            meta: { title: '创建股票持仓', keepAlive: true, permission: ['createfinancing'] }
          }
        ]
      },
      // 新股管理
      {
        path: '/newshares',
        redirect: '/newshares/newshareslist',
        component: RouteView,
        meta: { title: '新股管理', icon: 'sliders', permission: ['newshareslist'] },
        children: [
          {
            path: '/newshares/newshareslist',
            name: 'newshareslist',
            component: () => import('@/views/newshares/newshareslist'),
            meta: { title: '新股列表', keepAlive: true, permission: ['newshareslist'] }
          },
          {
            path: '/newshares/newsharesrecord',
            name: 'newsharesrecord',
            component: () => import('@/views/newshares/newsharesrecord'),
            meta: { title: '新股申购记录', keepAlive: true, permission: ['newsharesrecord'] }
          },
          // {
          //   path: '/newshares/newsharesqclist',
          //   name: 'newsharesqclist',
          //   component: () => import('@/views/newshares/newsharesqclist'),
          //   meta: { title: '新股抢筹列表', keepAlive: true, permission: ['newsharesqclist'] }
          // },
          {
            path: '/newshares/newsharesqcrecord',
            name: 'newsharesqcrecord',
            component: () => import('@/views/newshares/newsharesqcrecord'),
            meta: { title: '新股抢筹记录', keepAlive: true, permission: ['newsharesqcrecord'] }
          },
          {
            path: '/newshares/dazonglist',
            name: 'dazonglist',
            component: () => import('@/views/newshares/dazonglist'),
            meta: { title: '大宗交易列表', keepAlive: true, permission: ['dazonglist'] }
          }
        ]
      },
      // 资金管理
      {
        path: '/capital',
        redirect: '/capital/rechargelist',
        component: RouteView,
        meta: { title: '资金管理', icon: 'dollar', permission: ['rechargelist'] },
        children: [
          {
            path: '/capital/rechargelist',
            name: 'rechargelist',
            component: () => import('@/views/capital/rechargelist'),
            meta: { title: '充值列表', keepAlive: true, permission: ['rechargelist'] }
          },
          {
            path: '/capital/withdrawallist',
            name: 'withdrawallist',
            component: () => import('@/views/capital/withdrawallist'),
            meta: { title: '提现列表', keepAlive: true, permission: ['withdrawallist'] }
          },
          {
            path: '/capital/fundrecords',
            name: 'fundrecords',
            component: () => import('@/views/capital/fundrecords'),
            meta: { title: '资金记录', keepAlive: true, permission: ['fundrecords'] }
          },
          {
            path: '/capital/fundtransferrecord',
            name: 'fundtransferrecord',
            component: () => import('@/views/capital/fundTransferrecord'),
            meta: { title: '资金互转记录', keepAlive: true, permission: ['fundtransferrecord'] }
          }

        ]
      },
      // 日志管理
      {
        path: '/logmanage',
        redirect: '/logmanage/loginlog',
        component: RouteView,
        meta: { title: '日志管理', icon: 'solution', permission: ['loginlog'] },
        children: [
          {
            path: '/logmanage/loginlog',
            name: 'loginlog',
            component: () => import('@/views/logmanage/loginlog'),
            meta: { title: '登录日志', keepAlive: true, permission: ['loginlog'] }
          },
          {
            path: '/logmanage/smslog',
            name: 'smslog',
            component: () => import('@/views/logmanage/smslog'),
            meta: { title: '短信日志', keepAlive: true, permission: ['smslog'] }
          },
          {
            path: '/logmanage/scheduledtasks',
            name: 'scheduledtasks',
            component: () => import('@/views/logmanage/scheduledTasks'),
            meta: { title: '定时任务', keepAlive: true, permission: ['scheduledtasks'] }
          },
          {
            path: '/logmanage/stationmessage',
            name: 'stationmessage',
            component: () => import('@/views/logmanage/stationmessage'),
            meta: { title: '站内消息', keepAlive: true, permission: ['stationmessage'] }
          }
        ]
      },

      // 管理设置
      {
        path: '/managesettings',
        redirect: '/managesettings/managelist',
        component: RouteView,
        meta: { title: '管理设置', icon: 'control', permission: ['managelist'] },
        children: [
          {
            path: '/managesettings/managelist',
            name: 'managelist',
            component: () => import('@/views/managesettings/managelist'),
            meta: { title: '管理列表', keepAlive: true, permission: ['managelist'] }
          }
        ]
      },
      // 风控设置
      {
        path: '/risksetting',
        redirect: '/risksetting/productsetting',
        component: RouteView,
        meta: { title: '风控设置', icon: 'warning', permission: ['productsetting'] },
        children: [
          {
            path: '/risksetting/productsetting',
            name: 'productsetting',
            component: () => import('@/views/risksetting/productsetting'),
            meta: { title: '产品配置', keepAlive: true, permission: ['productsetting'] }
          },
          {
            path: '/risksetting/sharessetting',
            name: 'sharessetting',
            component: () => import('@/views/risksetting/sharessetting'),
            meta: { title: '股票风控', keepAlive: true, permission: ['sharessetting'] }
          },
          {
            path: '/risksetting/indexsetting',
            name: 'indexsetting',
            component: () => import('@/views/risksetting/indexsetting'),
            meta: { title: '指数风控', keepAlive: true, permission: ['indexsetting'] }
          },

          {
            path: '/risksetting/spreadsetting',
            name: 'spreadsetting',
            component: () => import('@/views/risksetting/spreadsetting'),
            meta: { title: '点差设置', keepAlive: true, permission: ['spreadsetting'] }
          }

        ]
      },
      // 系统设置
      {
        path: '/allsetting',
        redirect: '/allsetting/noticesetting',
        component: RouteView,
        meta: { title: '系统设置', icon: 'setting', permission: ['noticesetting'] },
        children: [
          {
            path: '/allsetting/noticesetting',
            name: 'noticesetting',
            component: () => import('@/views/allsetting/noticesetting'),
            meta: { title: '公告设置', keepAlive: true, permission: ['noticesetting'] }
          },
          {
            path: '/allsetting/bannersetting',
            name: 'bannersetting',
            component: () => import('@/views/allsetting/bannersetting'),
            meta: { title: '轮播图设置', keepAlive: true, permission: ['bannersetting'] }
          },
          {
            path: '/allsetting/paysetting',
            name: 'paysetting',
            component: () => import('@/views/allsetting/paysetting'),
            meta: { title: '支付渠道设置', keepAlive: true, permission: ['paysetting'] }
          }

        ]
      },

      // forms
      // {
      //   path: '/form',
      //   redirect: '/form/base-form',
      //   component: RouteView,
      //   meta: { title: 'menu.form', icon: 'form', permission: ['form'] },
      //   children: [
      //     {
      //       path: '/form/base-form',
      //       name: 'BaseForm',
      //       component: () => import('@/views/form/basicForm'),
      //       meta: { title: 'menu.form.basic-form', keepAlive: true, permission: ['form'] }
      //     },
      //     {
      //       path: '/form/step-form',
      //       name: 'StepForm',
      //       component: () => import('@/views/form/stepForm/StepForm'),
      //       meta: { title: 'menu.form.step-form', keepAlive: true, permission: ['form'] }
      //     },
      //     {
      //       path: '/form/advanced-form',
      //       name: 'AdvanceForm',
      //       component: () => import('@/views/form/advancedForm/AdvancedForm'),
      //       meta: { title: 'menu.form.advanced-form', keepAlive: true, permission: ['form'] }
      //     }
      //   ]
      // },
      // list
      // {
      //   path: '/list',
      //   name: 'list',
      //   component: RouteView,
      //   redirect: '/list/table-list',
      //   meta: { title: 'menu.list', icon: 'table', permission: ['table'] },
      //   children: [
      //     {
      //       path: '/list/table-list/:pageNo([1-9]\\d*)?',
      //       name: 'TableListWrapper',
      //       hideChildrenInMenu: true, // 强制显示 MenuItem 而不是 SubMenu
      //       component: () => import('@/views/list/TableList'),
      //       meta: { title: 'menu.list.table-list', keepAlive: true, permission: ['table'] }
      //     },
      //     {
      //       path: '/list/basic-list',
      //       name: 'BasicList',
      //       component: () => import('@/views/list/BasicList'),
      //       meta: { title: 'menu.list.basic-list', keepAlive: true, permission: ['table'] }
      //     },
      //     {
      //       path: '/list/card',
      //       name: 'CardList',
      //       component: () => import('@/views/list/CardList'),
      //       meta: { title: 'menu.list.card-list', keepAlive: true, permission: ['table'] }
      //     },
      //     {
      //       path: '/list/search',
      //       name: 'SearchList',
      //       component: () => import('@/views/list/search/SearchLayout'),
      //       redirect: '/list/search/article',
      //       meta: { title: 'menu.list.search-list', keepAlive: true, permission: ['table'] },
      //       children: [
      //         {
      //           path: '/list/search/article',
      //           name: 'SearchArticles',
      //           component: () => import('../views/list/search/Article'),
      //           meta: { title: 'menu.list.search-list.articles', permission: ['table'] }
      //         },
      //         {
      //           path: '/list/search/project',
      //           name: 'SearchProjects',
      //           component: () => import('../views/list/search/Projects'),
      //           meta: { title: 'menu.list.search-list.projects', permission: ['table'] }
      //         },
      //         {
      //           path: '/list/search/application',
      //           name: 'SearchApplications',
      //           component: () => import('../views/list/search/Applications'),
      //           meta: { title: 'menu.list.search-list.applications', permission: ['table'] }
      //         }
      //       ]
      //     }
      //   ]
      // },

      // profile
      // {
      //   path: '/profile',
      //   name: 'profile',
      //   component: RouteView,
      //   redirect: '/profile/basic',
      //   meta: { title: 'menu.profile', icon: 'profile', permission: ['profile'] },
      //   children: [
      //     {
      //       path: '/profile/basic',
      //       name: 'ProfileBasic',
      //       component: () => import('@/views/profile/basic'),
      //       meta: { title: 'menu.profile.basic', permission: ['profile'] }
      //     },
      //     {
      //       path: '/profile/advanced',
      //       name: 'ProfileAdvanced',
      //       component: () => import('@/views/profile/advanced/Advanced'),
      //       meta: { title: 'menu.profile.advanced', permission: ['profile'] }
      //     }
      //   ]
      // },

      // result
      // {
      //   path: '/result',
      //   name: 'result',
      //   component: RouteView,
      //   redirect: '/result/success',
      //   meta: { title: 'menu.result', icon: 'check-circle-o', permission: ['result'] },
      //   children: [
      //     {
      //       path: '/result/success',
      //       name: 'ResultSuccess',
      //       component: () => import(/* webpackChunkName: "result" */ '@/views/result/Success'),
      //       meta: { title: 'menu.result.success', keepAlive: false, hiddenHeaderContent: true, permission: ['result'] }
      //     },
      //     {
      //       path: '/result/fail',
      //       name: 'ResultFail',
      //       component: () => import(/* webpackChunkName: "result" */ '@/views/result/Error'),
      //       meta: { title: 'menu.result.fail', keepAlive: false, hiddenHeaderContent: true, permission: ['result'] }
      //     }
      //   ]
      // },

      // Exception
      // {
      //   path: '/exception',
      //   name: 'exception',
      //   component: RouteView,
      //   redirect: '/exception/403',
      //   meta: { title: 'menu.exception', icon: 'warning', permission: ['exception'] },
      //   children: [
      //     {
      //       path: '/exception/403',
      //       name: 'Exception403',
      //       component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/403'),
      //       meta: { title: 'menu.exception.not-permission', permission: ['exception'] }
      //     },
      //     {
      //       path: '/exception/404',
      //       name: 'Exception404',
      //       component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
      //       meta: { title: 'menu.exception.not-find', permission: ['exception'] }
      //     },
      //     {
      //       path: '/exception/500',
      //       name: 'Exception500',
      //       component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/500'),
      //       meta: { title: 'menu.exception.server-error', permission: ['exception'] }
      //     }
      //   ]
      // },

      // account
      {
        path: '/account',
        component: RouteView,
        redirect: '/account/center',
        name: 'account',
        meta: { title: '个人中心', icon: 'user', keepAlive: true, permission: ['user'] },
        children: [
          // {
          //   path: '/account/center',
          //   name: 'center',
          //   component: () => import('@/views/account/center'),
          //   meta: { title: 'menu.account.center', keepAlive: true, permission: ['user'] }
          // },
          {
            path: '/account/settings',
            name: 'settings',
            component: () => import('@/views/account/settings/Index'),
            meta: { title: 'menu.account.settings', hideHeader: true, permission: ['user'] },
            redirect: '/account/settings/basic',
            hideChildrenInMenu: true,
            children: [
              {
                path: '/account/settings/basic',
                name: 'BasicSettings',
                component: () => import('@/views/account/settings/BasicSetting'),
                meta: { title: 'account.settings.menuMap.basic', hidden: true, permission: ['user'] }
              },
              {
                path: '/account/settings/security',
                name: 'SecuritySettings',
                component: () => import('@/views/account/settings/Security'),
                meta: {
                  title: 'account.settings.menuMap.security',
                  hidden: true,
                  keepAlive: true,
                  permission: ['user']
                }
              },
              {
                path: '/account/settings/custom',
                name: 'CustomSettings',
                component: () => import('@/views/account/settings/Custom'),
                meta: { title: 'account.settings.menuMap.custom', hidden: true, keepAlive: true, permission: ['user'] }
              },
              {
                path: '/account/settings/binding',
                name: 'BindingSettings',
                component: () => import('@/views/account/settings/Binding'),
                meta: { title: 'account.settings.menuMap.binding', hidden: true, keepAlive: true, permission: ['user'] }
              },
              {
                path: '/account/settings/notification',
                name: 'NotificationSettings',
                component: () => import('@/views/account/settings/Notification'),
                meta: {
                  title: 'account.settings.menuMap.notification',
                  hidden: true,
                  keepAlive: true,
                  permission: ['user']
                }
              }
            ]
          }
        ]
      }

      // other
      /*
      {
        path: '/other',
        name: 'otherPage',
        component: PageView,
        meta: { title: '其他组件', icon: 'slack', permission: [ 'dashboard' ] },
        redirect: '/other/icon-selector',
        children: [
          {
            path: '/other/icon-selector',
            name: 'TestIconSelect',
            component: () => import('@/views/other/IconSelectorView'),
            meta: { title: 'IconSelector', icon: 'tool', keepAlive: true, permission: [ 'dashboard' ] }
          },
          {
            path: '/other/list',
            component: RouteView,
            meta: { title: '业务布局', icon: 'layout', permission: [ 'support' ] },
            redirect: '/other/list/tree-list',
            children: [
              {
                path: '/other/list/tree-list',
                name: 'TreeList',
                component: () => import('@/views/other/TreeList'),
                meta: { title: '树目录表格', keepAlive: true }
              },
              {
                path: '/other/list/edit-table',
                name: 'EditList',
                component: () => import('@/views/other/TableInnerEditList'),
                meta: { title: '内联编辑表格', keepAlive: true }
              },
              {
                path: '/other/list/user-list',
                name: 'UserList',
                component: () => import('@/views/other/UserList'),
                meta: { title: '用户列表', keepAlive: true }
              },
              {
                path: '/other/list/role-list',
                name: 'RoleList',
                component: () => import('@/views/other/RoleList'),
                meta: { title: '角色列表', keepAlive: true }
              },
              {
                path: '/other/list/system-role',
                name: 'SystemRole',
                component: () => import('@/views/role/RoleList'),
                meta: { title: '角色列表2', keepAlive: true }
              },
              {
                path: '/other/list/permission-list',
                name: 'PermissionList',
                component: () => import('@/views/other/PermissionList'),
                meta: { title: '权限列表', keepAlive: true }
              }
            ]
          }
        ]
      } */

    ]
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      },
      {
        path: 'register',
        name: 'register',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Register')
      },
      {
        path: 'register-result',
        name: 'registerResult',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/RegisterResult')
      },
      {
        path: 'recover',
        name: 'recover',
        component: undefined
      }
    ]
  },

  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  }
]
