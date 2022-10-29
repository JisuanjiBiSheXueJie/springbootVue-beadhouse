import {createRouter, createWebHistory} from 'vue-router'
import UserLayout from "../layout/UserLayout.vue";
import UserBxApply from "../views/User/BX/Apply";
import UserBxQuery from "../views/User/BX/Query";
import login from "../views/User/login";
import AdminLayout from "@/layout/AdminLayout";
import GuestLayout from "@/layout/GuestLayout";

const routes = [
    {
        path: '/generateCodevVisitors',
        name: 'generateCodevVisitors',
        component: () => import("@/views/Guest/generateCodevVisitors"),
    },
    {
        path: '/guest',
        name: 'guest',
        component: GuestLayout,
        redirect: "/guest/index",
        children: [
            {
                path: '/guest/index',
                name: 'guestIndex',
                component: () => import("@/views/Guest/index"),
            },
            {
                path: 'release',
                name: 'release',
                component: () => import("@/views/Guest/release"),
            },
            {
                path: 'learn',
                name: 'learn',
                component: () => import("@/views/Guest/learn"),
            },
            {
                path: 'examination',
                name: 'examination',
                component: () => import("@/views/Guest/examination"),
            },
            {
                path: 'about',
                name: 'about',
                component: () => import("@/views/Guest/about"),
            },
            {
                path: 'learnonline',
                name: 'learnonline',
                component: () => import("@/views/Guest/learnonline"),
            },
            {
                path: 'news',
                name: 'indexNews',
                component: () => import("@/views/Guest/news"),
            },
            {
                path: '/ContactUs',
                name: 'ContactUs',
                component: () => import("@/views/Guest/ContactUs")
            }
        ]
    },
    {
        path: '/',
        name: 'home',
        component: UserLayout,
    },
    {
        path: '/User/Login',
        name: 'Login',
        component: login,
    },
    // {
    //   path: '/admin',
    //   name: 'admin',
    //   component: AdminLayout
    // }
    {
        path: '/admin',
        name: 'AdminLayout',
        component: AdminLayout,
        redirect: "/admin/index",
        children: [
            {
                path: '/admin/index',
                name: 'index',
                component: () => import("@/views/Admin/index")
            },
            {
                path: '/admin/usermanagement',
                name: 'usermanagement',
                component: () => import("@/views/Admin/usermanagement")
            },
            {
                path: '/admin/bxManage',
                name: 'bxManage',
                component: () => import("@/views/Admin/bxManage")
            },
            {
                path: '/admin/leave',
                name: 'leave',
                component: () => import("@/views/Admin/leave")
            },
            {
                path: '/admin/visitor',
                name: 'visitor',
                component: () => import("@/views/Admin/visitor")
            },
            {
                path: '/admin/kehu/index',
                name: 'kehu',
                component: () => import("@/views/Admin/kehu/index")
            },
            {
                path: '/admin/news/index',
                name: 'news',
                component: () => import("@/views/Admin/news/index")
            },
            {
                path: '/admin/news/management',
                name: 'management',
                component: () => import("@/views/Admin/news/management")
            },
            {
                path: '/admin/news/IndexIntroduce',
                name: 'IndexIntroduce',
                component: () => import("@/views/Admin/news/IndexIntroduce")
            },
            {
                path: '/admin/monitor/index',
                name: 'monitor',
                component: () => import("@/views/Admin/monitor/index.vue")
            },
            {
                path: '/admin/health/index',
                name: 'health',
                component: () => import("@/views/Admin/health/index.vue")
            },
            {
                path: '/admin/setting/index',
                name: 'setting',
                component: () => import("@/views/Admin/setting/index.vue")
            },
            {
                path: '/admin/online/index',
                name: 'online',
                component: () => import("@/views/Admin/online/index.vue")
            },
            {
                path: '/admin/FoodAndMedicine/foodManagement',
                name: 'foodManagement',
                component: () => import("@/views/Admin/FoodAndMedicine/foodManagement.vue")
            },
            {
                path: '/admin/FoodAndMedicine/medlicineManagement',
                name: 'medlicineManagement',
                component: () => import("@/views/Admin/FoodAndMedicine/medlicineManagement.vue")
            },
            {
                path: '/admin/MessageManagement',
                name: 'MessageManagement',
                component: () => import("@/views/Admin/MessageManagement.vue")
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
