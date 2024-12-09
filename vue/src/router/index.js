import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/Admingl',
    name: 'Admingl',
    component: () =>import ('../views/Admingl.vue'), 
    //重定向
    redirect:'/Admingl/home',
    children:[
      {
        path: 'home',
        name: 'home',
        component: () =>import ('../views/HomeView.vue') 
      },
      {
        path: 'admin',
        name: 'admin',
        component: () => import('../views/AdminView.vue')
      },
      {
        path: 'doctor',
        name: 'doctor',
        component: () => import('../views/DoctorView.vue')
      },
      {
        path: 'user',
        name: 'user',
        component: () => import('../views/UserView.vue')
      },
      {
        path: 'keshi',
        name: 'keshi',
        component: () => import('../views/Keshiview.vue')
      },
      {
        path: 'doctorguahao',
        name: 'doctorguahao',
        component: () => import('../views/doctorguahao.vue')
      }
    ]
  },

  {
    path: '/',
    name: 'duankou',
    component: () =>import ('/'),
    redirect:'/usermain' //重定向
  },

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/AdminLoginView.vue'),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/AdminRegister.vue'),
  },

  {
    path: '/userlogin',
    name: 'userlogin',
    component: () => import('../views/UserLogin.vue'),
  },

  {
    path: '/userregister',
    name: 'userregister',
    component: () => import('../views/UserRegister.vue'),
  },

  {
    path: '/usermain',
    name: 'usermain',
    component: () => import('../views/Usermain.vue'),
    redirect:'/usermain/usermain1',
    children:[
      {
        path: 'usermain1',
        name: 'usermain1',
        component: () => import('../views/Usermain1.vue'),
      },
     
      {
        path: 'usermain2',
        name: 'usermain2',
        component: () => import('../views/Usermain2.vue'),
      },
      {
        path: 'usermain3',
        name: 'usermain3',
        component: () => import('../views/Usermain3.vue'),
      },
      {
        path: 'usermain4',
        name: 'usermain3',
        component: () => import('../views/Usermain4.vue'),
      },
      ]


  },

  {
    path: '/doctorlogin',
    name: 'doctorlogin',
    component: () => import('../views/DoctorLogin.vue'),
  },

  {
    path: '/doctorregister',
    name: 'doctorregister',
    component: () => import('../views/DoctorRegister.vue'),
  },

  {
    path: '/doctormain',
    name: 'doctormain',
    component: () => import('../views/Doctormain.vue'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  // 检查是否是登录或注册页面
  if (to.path === '/login' ||
     to.path === '/register'||
     to.path === '/userlogin' || 
     to.path === '/userregister'||
     to.path === '/doctorlogin' || 
     to.path === '/doctorregister'||
     to.path === '/usermain'||
     to.path === '/doctormain'||
     to.path === '/usermain/usermain1'
     ||to.path === '/usermain/usermain2'
     ||to.path === '/usermain/usermain3'
     ||to.path === '/usermain/usermain4') {
    next(); // 如果是登录或注册页面，直接放行
  } else {
    const admin = localStorage.getItem("admin");
    if (!admin) {
      return next("/login"); // 如果不是登录或注册页面，且没有admin信息，则重定向到登录页面
    }
    next(); // 如果有admin信息，放行
  }
});

export default router
