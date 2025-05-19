// 单位用千位分隔符显示
export function thousandBitSeparator (str) {
  var num = parseFloat(str).toFixed(3) // 这里因为我需要两位小数所以做一个限制，你们看情况做小数位的限制
  var s = num.substring(0, (num.length - 1)) // 只取小数位2位
  return s && s.toString().replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
    return $1 + ','
  })
}

// 判断是不是空 是--true
export function isNull (str) {
  if (str === '' || str === null) {
    return true
  } else {
    return false
  }
}
export function debounce (fn, delay) {
  // 时间期限
  var delay = delay || 200;
  var timer;
  // 闭包
  return function () {
    // 考虑作用域，上下文环境，apply需要用到this对象
    var th = this;
    // 接收的参数用 ES6 中的 rest 参数统一存储到变量 args 中。arguments就是传入的参数数组,而且个数可以不确定的传回给fn（不确定函数到底有多少个参数，用arguments来接收）
    var args = arguments;
    // 判断还在定时，说明当前正在一个计时过程中，并且又触发了相同事件。所以要取消当前的计时，重新开始计时
    if (timer) {
      clearTimeout(timer);
    }
    timer = setTimeout(function () {
      timer = null;
      // 执行方法
      fn.apply(th, args);
    }, delay);
  };
}
// 判断是不是邮箱 是--true
export function isEmail (str) {
  let myreg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
  return myreg.test(str)
}

// 只能中文
export function isName (str) {
  let myreg = /^[\u4E00-\u9FA5]+$/
  return myreg.test(str)
}

// 手机号码
export function isPhone (mobile) {
  // let myreg = /^[1][3,4,5,7,8][0-9]{9}$/  //手机号码验证
  let myreg = /^[0-9]{11}$/ // 手机号码验证
  return myreg.test(mobile)
}

// 密码 校验
export function pwdReg (psd) {
  let value = psd.replace(/\s*/g, '')
  let myreg = /^[a-zA-Z0-9!@#$%^&*.]{6,12}$/ // 手机号码验证
  return myreg.test(value)
}

// 身份证校验
export function idCardReg (idcard) {
  var myreg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
  return myreg.test(idcard)
}

// 银行卡校验
export function bankNoReg (val) {
  var myreg = /^([1-9]{1})(\d{14,18})$/
  return myreg.test(val)
}

// 数字隐藏显示 *
export function hideNumberTo (str) {
  let number = str.toFixed(2)
  let arr = number.split('.')
  let string = ''
  for (var i = 0; i < arr[0].length; i++) {
    string += '*'
  }
  return string + '.**'
}

// 时间戳转换为时间格式
export function timeFormat (date) {
  let fmt = 'yyyy-MM-dd hh:mm:ss'
  // let date = new Date(time)
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  }
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + ''
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str))
    }
  }
  return fmt
}

function padLeftZero (str) {
  return ('00' + str).substr(str.length)
}

// cookie 操作
export function setCookie (name, value, day) {
  if (day !== 0) { // 当设置的时间等于0时，不设置expires属性，cookie在浏览器关闭后删除
    var curDate = new Date()
    var curTamp = curDate.getTime()
    var curWeeHours = new Date(curDate.toLocaleDateString()).getTime() - 1
    var passedTamp = curTamp - curWeeHours
    var leftTamp = 24 * 60 * 60 * 1000 - passedTamp
    var leftTime = new Date()
    leftTime.setTime(leftTamp + curTamp)
    document.cookie = name + '=' + escape(value) + ';expires=' + leftTime.toGMTString()
  } else {
    document.cookie = name + '=' + escape(value)
  }
}

// get
export function getCookie (name) {
  var arr
  var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
  arr = document.cookie.match(reg)
  if (arr) {
    return unescape(arr[2])
  } else {
    return null
  }
  // document.cookie = name + "=" + escape(value);
}

export function clearCookie () {
  this.setCookie('USER_TOKEN', '', -1)
}

//
export function checkCookie () {
  var user = this.getCookie('USER_TOKEN')
  if (user !== '') {
    alert('Welcome again ' + user)
  } else {
    user = prompt('Please enter your name:', '')
    if (user !== '' && user != null) {
      this.setCookie('USER_TOKEN', user, 365)
    }
  }
}
