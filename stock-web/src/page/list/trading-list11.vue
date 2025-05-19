<template>
  <div class="tr_list_page">
    <div class="content">
      <div class="tabs">
        <div class="bottom_content" v-if="tabsIndex === 1">
          <!-- 搜索框 -->
          <div class="search">
            <div class="search_content">
              <div class="left_search">
                <div class="search_img">
                  <img src="../../assets/img/searchs.png" alt />
                </div>
                <div class="search_input">
                  <input type="text" class="searchs" :placeholder="$t('hj37')" ref="search" id="sousuo" v-model="gpcode"
                    @input="gpinput" />
                </div>
              </div>
            </div>
          </div>
          <div class="tab_class">
            <div class="scroll_tab">
              <div class="tab_items" v-for="(item, index) in tabsClassArr" :key="index" :class="index == 0 ? 'mrt' : ''"
                @click="handleTabsItem(item, index)">
                <span :class="tabsItemIndex === index ? 'active' : ''">{{ item.name }}</span>
              </div>
            </div>
          </div>
          <div class="kai_mess" v-show="isToken == ''" @click="$router.push('/login')">
            <div class="left_identity_img">
              <img src="../../assets/img/shenfen2.png" alt />
            </div>
            <div class="text">
              <span>{{ $t('hj38') }}</span>
            </div>
            <div class="right_go">
              <img src="../../assets/img/yuoujiantou.png" alt />
            </div>
          </div>
          <div class="list_title">
            <div class="item_title varieties">
              <span>{{ $t('hj39') }}</span>
            </div>
            <div class="item_title latest_price">
              <span>{{ $t('hj40') }}</span>
            </div>
            <div class="item_title applies">
              <span v-show="tabsItemIndex != 5">{{ $t('hj41') }}</span>
              <span v-show="tabsItemIndex == 5" style="width: 100%;text-align: center;">{{ $t('hj42') }}</span>
            </div>
          </div>
          <!-- 市场列表 -->
          <div class="list" :class="isToken == '' ? 'listHeight' : 'listHeights'">
            <van-list v-model="loading" :finished="finished" :finished-text="$t('hj43')" @load="onLoad"
              :immediate-check="false">
              <div v-if="tabsItemIndex == 0" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
              <div  v-if="tabsItemIndex==1" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr1" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
              <div  v-if="tabsItemIndex==2" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr2" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
              <div  v-if="tabsItemIndex==3" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr3" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
              <div  v-if="tabsItemIndex==4" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr4" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
              <div  v-if="tabsItemIndex==5" class="van-clearfix">
                <div class="list_items">
                  <div class="item" v-for="(item, index) in listArr5" :key="index"
                    @click="handleGoToKlineDetail(item, index)">
                    <div v-if="(tabsItemIndex == 5 && item.zt == 0) || tabsItemIndex != 5">
                      <div class="left_title">
                        <div class="tp">
                          <span class="collection" @click.stop="options(item)">
                            <span v-show="tabsItemIndex != 5" class="shu"
                              :class="item.isOption == '1' ? 'shublue' : ''"></span>
                            <span v-show="tabsItemIndex == 5" class="shu hongse"></span>
                          </span>
                          <span class="title_color" style="white-space: nowrap;overflow: hidden;">
                            {{ tabsItemIndex == 5 ? item.name : tabsItemIndex ==
                                2 ? item.name : tabsItemIndex == 0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.name :
                                item.indexName | getName
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span>
                            {{ tabsItemIndex == 5 ? item.stockType + item.code : tabsItemIndex == 2 ? item.gid :
                                tabsItemIndex
                                  ==
                                  0 || tabsItemIndex == 3 || tabsItemIndex == 4 ? item.gid : item.indexCode
                            }}
                          </span>
                        </div>
                      </div>
                      <div class="center_price">
                        <div class="tp">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">
                            {{ item.nowPrice
                            }}
                          </span>
                          <span class="price_color" v-else>
                            {{ tabsItemIndex == 5 ? Number(item.price).toFixed(2) :
                                item.currentPoint
                            }}
                          </span>
                        </div>
                        <div class="bt">
                          <span v-if="tabsItemIndex == 5 || tabsItemIndex == 1">{{ tabsItemIndex == 5 ? 'Max:' +
                              Number(item.orderNumber) : $t('hj44') + ':' + Number(item.transFee)
                          }}</span>
                          <span v-else>{{ item.today_min }}</span>
                        </div>
                      </div>
                      <div class="right_bs">
                        <div class="tp" style="justify-content: flex-end;">
                          <span class="price_color"
                            v-if="tabsItemIndex == 0 || tabsItemIndex == 2 || tabsItemIndex == 3 || tabsItemIndex == 4"
                            :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                          <span class="price_color" v-if="tabsItemIndex == 1"
                            :class="item.floatRate > 0 ? 'green' : 'red'">
                            {{
                                item.floatRate + '%'
                            }}
                          </span>
                          <!-- <span class="price_color" v-if="tabsItemIndex == 3"
                          :class="((item.nowPrice - item.lastClose) / item.lastClose * 100).toFixed(2) > 0 ? 'green' : 'red'">
                          {{
                              item.lastClose == "" ? 0.00 : ((Number(item.nowPrice) - Number(item.lastClose)) /
                                Number(item.lastClose) * 100).toFixed(2) + '%'
                          }}
                        </span> -->
                          <div v-if="tabsItemIndex == 5" :class="item.type == 1 ? 'xgsgType' : 'xgsgTypeRed'">
                            <!-- {{item.orderNumber}} -->
                            {{ item.type == 1 ? $t('hj45') : $t('hj46') }}
                          </div>
                        </div>
                        <div class="bt" style="justify-content: flex-end;">
                          <div v-if="tabsItemIndex != 1 && tabsItemIndex != 5">{{ item.preclose_px }}</div>
                          <!-- class="codeIcon"  item.stock_type.toUpperCase()-->
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </van-list>


          </div>
        </div>
        <div class="bottom_content" v-if="tabsIndex === 0">
          <!-- 搜索框 -->
          <div class="search">
            <div class="search_content">
              <div class="left_search">
                <div class="search_img">
                  <img src="../../assets/img/searchs.png" alt />
                </div>
                <div class="search_input">
                  <input type="text" class="searchs" :placeholder="$t('hj37')"
                    onkeyup="value=value.replace(/[^a-zA-Z0-9]/g,'')" v-model="gpcodes" @input="gpinputs" />
                </div>
              </div>
            </div>
          </div>
          <div class="list_title">
            <div class="item_title varieties">
              <span>{{ $t('hj39') }}</span>
            </div>
            <div class="item_title latest_price">
              <span>{{ $t('hj40') }}</span>
            </div>
            <div class="item_title applies">
              <span>{{ $t('hj41') }}</span>
            </div>
          </div>


          <!-- 自选列表 -->
          <div class="list zxlist">
            <van-list v-model="loadings" :finished="finisheds" :finished-text="$t('hj43')" @load="onLoads"
              :immediate-check="false">
              <div class="van-clearfix">
                <div class="list_items">
                  <div class="item" @click="handleGoToKlineDetail1(item, index)" v-for="(item, index) in listArrs"
                    :key="item.indexCode">
                    <div class="left_title" style="flex-direction: column;align-items: flex-start;">
                      <div class="tp" style="display: flex;align-items: center;line-height: 2;">
                        <span class="collection" @click="optionszx(item)">
                          <span class="shu" :class="item.isOption == '1' ? 'shublue' : ''"></span>
                        </span>
                        <span class="title_color"
                          style="overflow: hidden;-webkit-line-clamp: 1;text-overflow: ellipsis;display: -webkit-box;-webkit-box-orient: vertical;">{{
                              item.stockName
                          }}</span>
                      </div>
                      <div class="bt">
                        <span>{{ item.stockGid }}</span>
                      </div>
                    </div>
                    <div class="center_price" style="align-items: center;">
                      <div class="tp">
                        <span class="price_color">{{ item.nowPrice }}</span>
                      </div>
                      <div class="bt">
                        <span>{{ item.addTime }}</span>
                      </div>
                    </div>
                    <div class="right_bs" style="justify-content: flex-end;align-items: center;">
                      <div class="tp">
                        <span class="price_color" :class="item.hcrate > 0 ? 'green' : 'red'">{{ item.hcrate }}</span>
                      </div>
                      <div class="bt">
                        <!-- <div>{{ item.stock_type.toUpperCase() }}</div> -->
                        <!-- class="codeIcon" -->
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </van-list>
          </div>
        </div>
      </div>
    </div>
    <div class="shai" v-if="dialogFlag" @click="openDialog()"></div>
    <div class="top_title" ref="topTitle">
      <div>
        <div class="tabs_title">
          <div class="tb" v-for="(item, index) in tabsArr" :key="index" @click="handleTabsClick(item, index)">
            <span :class="index === tabsIndex ? 'active' : ''">{{ item }}</span>
          </div>
        </div>
        <div class="right_money" @click="openDialog()">
          <div class="content_money" v-if="tabsIndex === 1">
            <div class="top_price">
              <div class="left">
                <span>{{ $t('hj47') }}</span>
              </div>
              <div class="right" v-if="userData.length == 0">¥0.00</div>
              <div class="right" v-if="userData.length != 0">
                <span v-if="$store.state.userInfo.userAmt == undefined">¥0.00</span>
                <span v-if="$store.state.userInfo && tabsItemIndex == 1" style="white-space: nowarp;">
                  {{ '¥' + $store.state.userInfo.userIndexAmt
                  }}
                </span>
                <span v-if="$store.state.userInfo.userAmt != undefined && (tabsItemIndex == 0)"
                  style="white-space: nowarp;">
                  {{ '¥ ' + $store.state.userInfo.userAmt
                  }}
                </span>
                <span v-if="$store.state.userInfo.userAmt != undefined && tabsItemIndex == 3"
                  style="white-space: nowarp;">
                  {{ '$ ' + (Number($store.state.userInfo.userAmt) / 7.1).toFixed(2)
                  }}
                </span>
                <span v-if="$store.state.userInfo.userAmt != undefined && tabsItemIndex == 4"
                  style="white-space: nowarp;">
                  {{ 'HK$ ' + (Number($store.state.userInfo.userAmt) / 0.9).toFixed(2)
                  }}
                </span>
                <span v-if="$store.state.userInfo.userAmt != undefined && tabsItemIndex == 2"
                  style="white-space: nowarp;">
                  {{ '¥ ' + $store.state.userInfo.userAmt
                  }}
                </span>
                <span v-if="$store.state.userInfo.userAmt != undefined && tabsItemIndex == 5"
                  style="white-space: nowarp;">
                  {{ '¥ ' + $store.state.userInfo.userAmt
                  }}
                </span>
                <!-- <span v-if="$store.state.userInfo && tabsItemIndex == 3" style="white-space: nowarp;">
                  {{
                      '¥' + $store.state.userInfo.userFuturesAmt
                  }}
                </span> -->
              </div>
              <div class="sanjiao">
                <img :class="dialogFlag ? 'xuanz' : ''" style="transition: all 0.5s;" src="../../assets/img/xiala.png"
                  alt />
              </div>
            </div>
            <div class="bottom_balance">
              <div>
                <span>{{ $t('hj48') }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="cards">
        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>{{ $t('hj49') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div v-if="tabsItemIndex != 3 && tabsItemIndex != 4">
              <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">
                ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userIndexAmt).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account">
                ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt).toFixed(2) }}</p>
              <!-- <p
                v-else-if="this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account"
              >
                ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt + $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}
              </p> -->
            </div>
            <div v-if="tabsItemIndex == 3">
              <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">
                $ {{ $store.state.hide ? '****' : Number(Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userIndexAmt) / 7.1).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account">
                $ {{ $store.state.hide ? '****' : Number(Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userFuturesAmt) / 7.1).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">$ {{ Number($store.state.hide ? '****' :
                    Number($store.state.userInfo.userAmt) / 7.1).toFixed(2)
                }}</p>
              <!-- <p
                v-else-if="this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account"
              >
                ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt + $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}
              </p> -->
            </div>
            <div v-if="tabsItemIndex == 4">
              <p v-if="this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">
                HK$ {{ $store.state.hide ? '****' : Number(Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userIndexAmt) / 0.9).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account">
                HK$ {{ $store.state.hide ? '****' : Number(Number($store.state.userInfo.userAmt +
                    $store.state.userInfo.userFuturesAmt) / 0.9).toFixed(2)
                }}
              </p>
              <p v-else-if="!this.$store.state.settingForm.indexDisplay && !this.$store.state.settingForm.futuresDisplay"
                class="account">HK$ {{ $store.state.hide ? '****' :
                    Number(Number($store.state.userInfo.userAmt) / 0.9).toFixed(2)
                }}</p>
              <!-- <p
                v-else-if="this.$store.state.settingForm.indexDisplay && this.$store.state.settingForm.futuresDisplay"
                class="account"
              >
                ¥{{ $store.state.hide ? '****' : Number($store.state.userInfo.userAmt +
                $store.state.userInfo.userIndexAmt + $store.state.userInfo.userFuturesAmt).toFixed(2)
                }}
              </p> -->
            </div>
          </div>
        </div>

        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>{{ $t('hj50') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div>
              <span v-show="tabsItemIndex == 1">
                {{ $store.state.hide ? '****' :
                    Number(($store.state.userInfo.enableIndexAmt +
                      $store.state.userInfo.allIndexFreezAmt) * indexSettingInfo.forceSellPercent).toFixed(2)
                }}
              </span>
              <span v-show="tabsItemIndex == 0 || tabsItemIndex == 5">
                {{ $store.state.hide ? '****' : Number(($store.state.userInfo.enableAmt
                    +
                    $store.state.userInfo.allFreezAmt) * settingInfo.forceStopPercent).toFixed(2)
                }}
              </span>
              <span v-show="tabsItemIndex == 3">
                {{ $store.state.hide ? '****' : Number(Number(($store.state.userInfo.enableAmt
                    +
                    $store.state.userInfo.allFreezAmt) * Number(settingInfo.forceStopPercent)) / 7.1).toFixed(2)
                }}
              </span>
              <span v-show="tabsItemIndex == 4">
                {{ $store.state.hide ? '****' : Number(Number(Number($store.state.userInfo.enableAmt
                    +
                    $store.state.userInfo.allFreezAmt) * Number(settingInfo.forceStopPercent)) / 0.9).toFixed(2)
                }}
              </span>
              <span v-show="tabsItemIndex == 2">
                {{ $store.state.hide ? '****' : Number(($store.state.userInfo.enableAmt
                    +
                    $store.state.userInfo.allFreezAmt) * settingInfo.forceStopPercent).toFixed(2)
                }}
              </span>
              <!-- <span v-show="tabsItemIndex == 3">
                {{ $store.state.hide ? '****' :
                    Number(($store.state.userInfo.enableFuturesAmt +
                      $store.state.userInfo.allFuturesFreezAmt) * futuresSettingInfo.forceSellPercent).toFixed(2)
                }}
              </span> -->
            </div>
          </div>
        </div>
        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>
                {{ tabsItemIndex == 1 ? $t('hj51') : tabsItemIndex == 0 ? $t('hj52') : tabsItemIndex == 2 ? $t('hj52') :
                    tabsItemIndex ==
                      5 ? $t('hj52') : ''
                }}
              </span>
              <span>{{ $t('hj53') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div>
              <span v-show="tabsItemIndex == 1">{{ '¥ ' + $store.state.userInfo.userIndexAmt }}</span>
              <span v-show="tabsItemIndex == 0 || tabsItemIndex == 5">{{ '¥ ' + $store.state.userInfo.userAmt }}</span>
              <span v-show="tabsItemIndex == 3">{{ '$ ' + (Number($store.state.userInfo.userAmt) / 7.1).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 4">{{ 'HK$ ' + (Number($store.state.userInfo.userAmt) / 0.9).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 2">{{ '¥ ' + $store.state.userInfo.userAmt }}</span>
              <!-- <span v-show="tabsItemIndex == 3">{{ '¥ ' + $store.state.userInfo.userFuturesAmt }}</span> -->
            </div>
          </div>
        </div>
        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>{{ $t('hj54') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div>
              <span v-show="tabsItemIndex == 1">{{ '¥ ' + $store.state.userInfo.enableIndexAmt }}</span>
              <span v-show="tabsItemIndex == 0 || tabsItemIndex == 5">{{ '¥ ' + $store.state.userInfo.enableAmt
              }}</span>
              <span v-show="tabsItemIndex == 3">{{ '$ ' + (Number($store.state.userInfo.enableAmt) / 7.1).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 4">{{ 'HK$ ' + (Number($store.state.userInfo.enableAmt) / 0.9).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 2">{{ '¥ ' + $store.state.userInfo.enableAmt }}</span>
              <!-- <span v-show="tabsItemIndex == 3">{{ '¥ ' + $store.state.userInfo.enableFuturesAmt }}</span> -->
            </div>
          </div>
        </div>
        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>{{ $t('hj55') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div>
              <span v-show="tabsItemIndex == 1">{{ '¥ ' + $store.state.userInfo.allIndexFreezAmt }}</span>
              <span v-show="tabsItemIndex == 0">{{ '¥ ' + $store.state.userInfo.allFreezAmt
              }}</span>
              <span v-show="tabsItemIndex == 5">{{ '¥ ' + $store.state.userInfo.djzj
              }}</span>
              <span v-show="tabsItemIndex == 3">{{ '$ ' + (Number($store.state.userInfo.allFreezAmt) / 7.1).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 4">{{ 'HK$ ' + (Number($store.state.userInfo.allFreezAmt) / 0.9).toFixed(2)
              }}</span>
              <span v-show="tabsItemIndex == 2">{{ '¥ ' + $store.state.userInfo.allFreezAmt }}</span>
              <!-- <span v-show="tabsItemIndex == 3">{{ '¥ ' + $store.state.userInfo.allFuturesFreezAmt }}</span> -->
            </div>
          </div>
        </div>
        <div class="card_item">
          <div class="tops_title">
            <div>
              <span>{{ $t('hj56') }}</span>
            </div>
          </div>
          <div class="bottom_price">
            <div>
              <span
                :class="$store.state.userInfo.allIndexProfitAndLose > 0 ? ' red' : $store.state.userInfo.allIndexProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 1">{{ '¥ ' + $store.state.userInfo.allIndexProfitAndLose }}</span>
              <span
                :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 0 || tabsItemIndex == 5">{{ '¥ ' + $store.state.userInfo.allProfitAndLose
                }}</span>
              <span
                :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 3">{{ '$ ' + (Number($store.state.userInfo.allProfitAndLose) / 7.1).toFixed(2)
                }}</span>
              <span
                :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 4">{{ 'HK$ ' + (Number($store.state.userInfo.allProfitAndLose) /
                    0.9).toFixed(2)
                }}</span>
              <span
                :class="$store.state.userInfo.allProfitAndLose > 0 ? ' red' : $store.state.userInfo.allProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 2">{{ '¥ ' + $store.state.userInfo.allProfitAndLose }}</span>
              <!-- <span
                :class="$store.state.userInfo.allFuturesProfitAndLose > 0 ? 'red' : $store.state.userInfo.allFuturesProfitAndLose < 0 ? ' green' : ''"
                v-show="tabsItemIndex == 3">{{ '¥' +Number($store.state.userInfo.allFuturesProfitAndLose).toFixed(2)}}</span> -->
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 新股弹窗 -->
    <van-popup v-model="settingDialog" position="bottom" :style="{ height: '35%' }" @close="popClose">
      <div class="setting_content">
        <div class="old_password">
          <div class="left_titles">
            <span>{{ $t('hj57') }}:</span>
          </div>
          <div class="right_password_input">
            <input type="number" v-model="sgNum" />
          </div>
        </div>
        <div class="btn_setting" @click="changeSg()">
          <span>{{ $t('hj58') }}</span>
        </div>
        <div class="shijian">
          <div class="xgsj">

            <div class="sjtlt">{{ $t('hj59') }}: </div>
            <div class="xgTime" v-if="this.sgsj">{{ this.sgsj | getTimeYear }}</div>
          </div>
          <div class="xgsj">
            <div class="sjtlt">{{ $t('hj60') }}: </div>
            <div class="xgTime" v-if="this.rjsj">{{ this.rjsj | getTimeYear }}</div>
          </div>
        </div>
        <!-- <div class="old_password">
          <div class="left_titles">
            <span>{{ '新密码:' }}</span>
          </div>
          <div class="right_password_input">
            <input type="password" v-model="newPassword" />
          </div>
        </div>
        <div class="old_password">
          <div class="left_titles">
            <span>{{ '确认新密码:' }}</span>
          </div>
          <div class="right_password_input">
            <input type="password" v-model="cirNewPassword" />
          </div>
        </div> -->

      </div>
    </van-popup>

  </div>
</template>

<script>
//Toast 
import { Toast } from 'vant';
import * as api from "@/axios/api";
import handleDt from "@/utils/deTh";
export default {
  name: "trading",
  components: {
  },
  data() {
    return {
      tabsIndex: 1,
      tabsArr: [this.$t('hj61'), this.$t('hj62')],
      tabClassActive: 1,
      dialogFlag: false,
      pageNum: 1,
      pageNums: 1,
      stockPlate: "",
      alertShow: false,
      isToken: "",
      elType: "warning",
      indexSettingInfo: {},
      futuresSettingInfo: {},
      settingInfo: {},
      loading: false,
      loadings: false,
      finished: false,
      finisheds: false,
      settingDialog: false,
      sgNum: 1,
      sgCode: '',
      gpcode: "",
      gpcodes: "",
      texts: "",
      sgsj: "",
      rjsj: "",
      orderNumber: "",
      elAlertShow: false,
      userData: [],
      elAlertText: "",
      stockType: '',
      tabsClassArr: [
        {
          name: this.$t('hj63'),
          type: 0
        },
        {
          name: this.$t('hj51'),
          type: 1
        },

        {
          name: this.$t('hj64'),
          type: 2
        },
        // {
        //   name: "期货",
        //   type: 3
        // }
        {
          name: this.$t('hj65'),
          type: 3
        },
        {
          name: this.$t('hj66'),
          type: 4
        },
        {
          name: this.$t('hj3'),
          type: 5
        }
      ],
      tabsItemIndex: 0,
      listArr: [],
      listArr1:[],
      listArr2:[],
      listArr3:[],
      listArr4:[],
      listArr5:[],
      listArrs: [],
    };
  },

  created() {
    if (this.$route.query.type == 1) {
      //选中sousuo输入框
      this.$nextTick(() => {
        this.$refs.search.focus();
      });
    }
    if (this.$route.query.listid) {
      this.tabsItemIndex = Number(this.$route.query.listid)
    }
  },

  mounted() {
    this.loading = true;
    this.getStock();
    this.isToken = window.localStorage.getItem("USERTOKEN");
    this.getUserInfo();
    this.getIndexSettingInfo();
    this.getSettingInfo();
    this.getFuturesSetting();
  },
  methods: {
    onLoad() {
      this.pageNum++;
      // 异步更新数据
      // setTimeout 仅做示例，真实场景中一般为 ajax 请求
      switch (this.tabsItemIndex) {
        case 1:
          this.loading = true;
          this.getListMarket();
          break;
        case 0:
          this.stockPlate = "";
          this.stockType = "";
          this.loading = true;
          this.getStock();
          break;
        case 2:
          this.stockPlate = "科创";
          this.stockType = "";
          this.loading = true;
          this.getStocks();
          break;
        case 3:
          this.stockPlate = "";
          this.stockType = "us";
          this.loading = true;
          this.getStockUs();
          break;
        case 4:
          this.stockPlate = "";
          this.stockType = "hk";
          this.loading = true;
          this.getStockHk();
          break;
        case 5:
          this.loading = true;
          this.getFutures();
          break;
      }

      // 加载状态结束
      // this.loading = false;

      // 数据全部加载完成
      // if (this.list.length >= 40) {
      //   this.finished = true;
      // }
    },
    gpinput: handleDt.debounce(function() {

      this.pageNum = 1;
      this.listArr = [];
      this.listArr1 = [];
      this.listArr2 = [];
      this.listArr3 = [];
      this.listArr4 = [];
      this.listArr5 = [];
      this.loading = true;
      this.finished = false;
      this.stockType = '';
      switch (this.tabsItemIndex) {
        case 1:
          this.getListMarket();
          break;
        case 0:
          this.stockPlate = "";
          this.stockType = '';
          this.getStock();
          break;
        case 2:
          this.stockPlate = "科创";
          this.stockType = '';
          this.getStocks();
          break;
        case 3:
          this.stockPlate = "";
          this.stockType = 'us';
          this.getStockUs();
          break;
        case 4:
          this.stockPlate = "";
          this.stockType = 'hk';
          this.getStockHk();
          break;
        case 5:
          this.getFutures();
          break;
      }
    }, 1000),
    gpinputs() {
      this.pageNums = 1;
      this.listArrs = [];
      this.loadings = true;
      this.finisheds = false;
      this.getMyList();
    },
    onLoads() {
      this.pageNums++;
      this.loadings = true;
      this.getMyList();
    },
    closeAlert() {
      this.alertShow = false;
    },
    async getUserInfo() {
      // 获取用户信息
      //   let showcookie = this.getCookie('USER_TOKEN');
      let data = await api.getUserInfo();
      if (data.status === 0) {
        // this.getProductSetting()

        this.$store.state.userInfo = data.data;
        this.userData = data.data;
      } else {


      }
      this.$store.state.user = this.user;
    },
    async getIndexSettingInfo() {
      // 网站设置信息 指数
      let data = await api.getIndexSetting();
      if (data.status === 0) {
        // 成功
        this.indexSettingInfo = data.data;
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
    },
    async getFuturesSetting() {
      // 网站设置信息 期货
      let data = await api.getFuturesSetting();
      if (data.status === 0) {
        // 成功
        this.futuresSettingInfo = data.data;
      } else {
        this.$store.commit('elAlertShow', { 'elAlertShow': true, 'elAlertText': data.msg });
      }
    },
    async getSettingInfo() {
      let data = await api.getSetting();
      if (data.status === 0) {
        // 成功
        this.settingInfo = data.data;
      } else {
        ` this.$store.commit('elAlertShow',{'elAlertShow':true,'elAlertText': data.msg});`
      }
    },
     getListMarket:  handleDt.debounce(async function() {
      let val = {
        pageNum: this.pageNum,
        pageSize: 15
      };
      // 获取指数列表
      let result = await api.getListMarket(val);
      this.loading = false;
      if (result.status === 0) {
        if (this.tabsItemIndex == 1) {
          this.listArr1 = result.data;
          this.finished = true;
        }
      } else {
        this.texts = result.msg;
        this.alertShow = true;
      }
    }, 500),
   getStock:  handleDt.debounce(async function() {
      //沪深
      let opt = {
        pageNum: this.pageNum,
        pageSize: 15,
        stockPlate: this.stockPlate,
        keyWords: this.gpcode,
        stockType: this.stockType
      };
      let data = await api.getStock(opt);
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        if (this.tabsItemIndex == 0) {
          if (this.gpcode) {
            
              this.listArr = data.data.list;
              
          } else {
            data.data.list.forEach(element => {
              this.listArr.push(element); 
            });
          }
        }
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    }, 500),
     getStockUs:  handleDt.debounce(async function() {
      //美股
      let opt = {
        pageNum: this.pageNum,
        pageSize: 15,
        stockPlate: this.stockPlate,
        keyWords: this.gpcode,
        stockType: this.stockType
      };
      let data = await api.getStock(opt);
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        if ( this.tabsItemIndex == 3) {
          if (this.gpcode) {
              this.listArr3 = data.data.list;  
          } else {
            data.data.list.forEach(element => {
              this.listArr3.push(element);
            });
          }
        }
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    },500) ,
     getStockHk:  handleDt.debounce(async function() {
      //港股
      let opt = {
        pageNum: this.pageNum,
        pageSize: 15,
        stockPlate: this.stockPlate,
        keyWords: this.gpcode,
        stockType: this.stockType
      };
      let data = await api.getStock(opt);
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        if ( this.tabsItemIndex == 4) {
          if (this.gpcode) {
              this.listArr4 = data.data.list;  
          } else {
            data.data.list.forEach(element => {
              this.listArr4.push(element);
            });
          }
        }
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    },500) ,
     getStocks:  handleDt.debounce(async function() {
      //科创
      let opt = {
        pageNum: this.pageNum,
        pageSize: 15,
        stockPlate: this.stockPlate,
        keyWords: this.gpcode
      };
      let data = await api.getStock(opt);
      this.loading = false;
      if (data.status === 0) {
        if (data.data.list.length < 15) {
          this.finished = true;
        }
        if (this.tabsItemIndex == 2) {
          if (this.gpcode) {
            this.listArr2 = data.data.list;
          } else {
            data.data.list.forEach(element => {
              this.listArr2.push(element);
            });
          }
        }
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    },500),

    popClose() {
      this.sgCode = '';
      this.sgsj = '';
      this.rjsj = '';
      this.orderNumber = '';
    },
    async changeSg() {
      if (!this.sgNum) {
        Toast(this.$t('hj67'));
        return;
      };
      //this.sgNum必须是数字并且不能小于1
      if (this.sgNum < 1) {
        Toast(this.$t('hj68'));
        return;
      }
      if (this.sgNum > this.orderNumber) {
        Toast(this.$t('hj69') + this.orderNumber);
        return;
      }

      let opt = {
        newCode: this.sgCode,
        applyNums: this.sgNum,
        phone: this.$store.state.userInfo.phone,
      };
      let data = await api.getNewAdd(opt);
      if (data.status === 0) {
        this.sgShow = false;
        this.sgCode = '';
        this.sgsj = '';
        this.rjsj = '';
        this.orderNumber = '';
        Toast(this.$t('hj70'));
        this.$router.push({ path: '/warehouse?index=3' });
      } else {
        Toast(data.msg);
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    handleGoToKlineDetail1(item) {
      var codes = item.stockCode;
      var names = item.stockName;
      var if_zhishu = '0';
      var if_us = item.stock_type == 'us' ? '1' : item.stock_type == 'hk' ? '2' : '';
      this.$router.push({
        path: "/kline",
        query: {
          name: names,
          code: codes,
          if_us: if_us,
          if_zhishu: if_zhishu,
          sok: item.type ? item.type : this.filterSH(item.stock_type),
          type: item.stock_type
        }
      });
    },
    //进入详情
    handleGoToKlineDetail(item) {
      if (this.userData.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      var codes = "";
      var names = "";
      var stock_type = "";
      var soks = "";
      var if_zhishu = '0';
      var if_us = '';
      switch (this.tabsItemIndex) {
        case 0:
          codes = item.code;
          names = item.name;
          stock_type = item.stock_type == 'us' ? item.stock_type + 'a' : item.stock_type;
          soks = item.type ? item.type : this.filterSH(item.stock_type);
          if_zhishu = '0';
          if_us = item.stock_type == 'us' ? '1' : '';
          break;
        case 3:
          codes = item.code;
          names = item.name;
          stock_type = item.stock_type + 'a';
          if_us = '1';
          soks = item.type;
          if_zhishu = '0';
          break;
        case 4:
          codes = item.code;
          names = item.name;
          stock_type = item.stock_type;
          soks = item.type;
          if_zhishu = '0';
          if_us = '2';
          break;
        case 1:
          codes = item.indexGid;
          names = item.indexName;
          stock_type = 'sh';
          if_zhishu = item.indexCode;
          soks = item.type ? item.type : 0;
          break;
        case 2:
          codes = item.code;
          names = item.name;
          stock_type = item.stock_type;
          soks = this.filterSH(item.stock_type);
          if_zhishu = '0';
          break;
        case 5:
          this.sgCode = item.code;
          this.sgsj = item.subscribeTime;
          this.rjsj = item.subscriptionTime;
          this.orderNumber = item.orderNumber;
          this.settingDialog = true;
          return;
          codes = item.futuresGid;
          names = item.futuresName;
          soks = 0;
          stock_type = 'qh';
          if_zhishu = item.indexGid;
          break;
        default:
          break;
      }

      this.$router.push({
        path: "/kline",
        query: {
          name: names,
          stockplate: item.stock_plate,
          code: codes,
          type: stock_type,
          sok: soks,
          if_us: if_us,
          usType: item.type,
          if_zhishu: if_zhishu,
        }
      });
    },
    filterSH(val) {
      if (val === "sh") {
        return 1;
      } else if (val === "bj" || val === "sz") {
        return 0;
      }
    },

     getFutures:  handleDt.debounce(async function() {
      // 获取期货列表
      let opt = {


      };
      let data = await api.getNewGu(opt);
      this.loading = false;
      if (data.status === 0) {
        if (this.tabsItemIndex == 5) {
          this.listArr5 = data.data.list;
        }
        this.finished = true;
      } else {
        this.texts = data.msg;
        this.alertShow = true;
      }
    },500),
    // async getFutures() {
    //   // 获取期货列表
    //   let opt = {
    //     homeShow: 1,
    //     pageNum: this.pageNum,
    //     pageSize: 15
    //   };
    //   let data = await api.getListFutures(opt);
    //   this.loading = false;
    //   if (data.data.length < 15) {
    //     this.finished = true;
    //   }
    //   if (data.status === 0) {
    //     if (this.tabsItemIndex == 3) {
    //       data.data.forEach(element => {
    //         this.listArr.push(element);
    //       });
    //     }
    //   } else {
    //     this.texts = data.msg;
    //     this.alertShow = true;
    //   }
    // },
    async getMyList() {
      this.loadings = true;
      //获取自选列表
      let opt = {
        pageNum: this.pageNums,
        pageSize: 15,
        keyWords: this.gpcodes
      };
      let data = await api.getMyList(opt);
      this.loadings = false;
      if (data.status == 0) {
        data.data.list.forEach(element => {
          this.listArrs.push(element);
        });
      }
      if (data.data.list.length < 15) {
        this.finisheds = true;
      }
    },
    handleTabsClick(item, index) {
      if (this.userData.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (index == 0) {
        if (this.dialogFlag) {
          this.$refs["topTitle"].style.height = "1.2821rem";
          setTimeout(() => {
            this.dialogFlag = false;

            this.listArrs = [];
            this.pageNums = 1;
            this.finisheds = false;
            this.tabsIndex = index;
            this.getMyList();
          }, 800);
        } else {
          this.listArrs = [];
          this.pageNums = 1;
          this.finisheds = false;
          this.tabsIndex = index;
          this.getMyList();
        }
      } else {
        this.tabsIndex = index;
      }
    },
    handleTabsItem(item, index) {
      this.tabsItemIndex = index;
      this.pageNum = 1;
      this.finished = false;
      this.loading = true;
      switch (item.type) {
        case 1:
          this.listArr1 = [];
          this.getListMarket();
          break;
        case 0:
          this.stockPlate = "";
          this.listArr = [];
          this.stockType = '';
          this.getStock();
          break;
        case 2:
          this.stockPlate = "科创";
          this.stockType = '';
          this.listArr2 = [];
          this.getStocks();
          break;
        case 3:
          this.stockPlate = "";
          this.stockType = 'us';
          this.listArr3 = [];
          this.getStockUs();
          break;
        case 4:
          this.stockPlate = "";
          this.stockType = 'hk';
          this.listArr4 = [];
          this.getStockHk();
          break;
        case 5:
          this.listArr5 = [];
          this.getFutures();
          break;
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async options(val) {
      if (this.tabsItemIndex == 1) {
        this.$message({
          message: this.$t('hj71'),
          type: 'warning'
        });
        return;
      }
      var codes = "";
      switch (this.tabsItemIndex) {
        case 0:
          codes = val.code;
          break;
        case 1:
          codes = val.indexGid;
          break;
        case 2:
          codes = val.code;
          break;
        case 3:
          codes = val.code;
          break;
        case 4:
          codes = val.code;
          break;
        case 5:
          codes = val.futuresGid;
          break;
        default:
          break;
      }
      if (val.isOption == "1") {
        let data = await api.delOption({ code: codes });
        if (data.status === 0) {
          switch (this.tabsItemIndex) {
            case 1:
              this.pageNum = 1;
              this.listArr1 = [];
              this.loading = true;
              this.finished = false;
              this.getListMarket();
              break;
            case 0:
              this.stockPlate = "";
              this.pageNum = 1;
              this.stockType = '';
              this.loading = true;
              this.listArr = [];
              this.finished = false;
              this.getStock();
              break;
            case 2:
              this.stockPlate = "科创";
              this.pageNum = 1;
              this.stockType = '';
              this.loading = true;
              this.listArr2 = [];
              this.finished = false;
              this.getStocks();
              break;
            case 3:
              this.stockPlate = "";
              this.stockType = 'us';
              this.pageNum = 1;
              this.loading = true;
              this.listArr3 = [];
              this.finished = false;
              this.getStockUs();
              break;
            case 4:
              this.stockPlate = "";
              this.stockType = 'hk';
              this.pageNum = 1;
              this.loading = true;
              this.listArr4 = [];
              this.finished = false;
              this.getStockHk();
              break;
            case 5:
              this.listArr5 = [];
              this.loading = true;
              this.pageNum = 1;
              this.finished = false;
              this.getFutures();
              break;
          }
          this.refreshList();
        } else {
          console.log(data.msg);
        }
      } else {
        let data = await api.addOption({ code: codes });
        if (data.status === 0) {
          switch (this.tabsItemIndex) {
            case 1:
              this.listArr1 = [];
              this.pageNum = 1;
              this.finished = false;
              this.getListMarket();
              break;
            case 0:
              this.stockPlate = "";
              this.stockType = '';
              this.pageNum = 1;
              this.finished = false;
              this.listArr = [];
              this.getStock();
              break;
            case 2:
              this.stockPlate = "科创";
              this.stockType = '';
              this.pageNum = 1;
              this.finished = false;
              this.listArr2 = [];
              this.getStocks();
              break;
            case 3:
              this.stockPlate = "";
              this.pageNum = 1;
              this.finished = false;
              this.stockType = 'us';
              this.listArr3 = [];
              this.getStockUs();
              break;
            case 4:
              this.stockPlate = "";
              this.stockType = 'hk';
              this.pageNum = 1;
              this.finished = false;
              this.listArr4 = [];
              this.getStockHk();
              break;
            case 5:
              this.listArr5 = [];
              this.pageNum = 1;
              this.finished = false;
              this.getFutures();
              break;
          }
        } else {
          console.log(data.msg);
        }
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async optionszx(val) {
      let data = await api.delOption({ code: val.stockGid });
      if (data.status === 0) {
        this.listArrs = [];
        this.pageNums = 1;
        this.finisheds = false;
        this.getMyList();
      } else {
        console.log(data.msg);
      }
      if (navigator.vibrate) {
        // 支持
        navigator.vibrate([55]);
      }
    },
    async refreshList() {
      // 刷新指数
      if (this.loading) {
        return;
      }
      let opt = {
        pageNum: 1,
        pageSize: this.currentNum
      };
      let data = await api.getListMarket(opt);
      this.list = data.data;
    },
    handleCollectionClick(item) {
      item.collection = !item.collection;
    },
    openDialog() {
      if (this.userData.length == 0) {
        this.$store.commit('dialogVisible', true);
        return;
      }
      if (!this.dialogFlag) {
        this.$refs["topTitle"].style.height = "6.9rem";
      } else {
        this.$refs["topTitle"].style.height = "1.2821rem";
      }
      this.dialogFlag = !this.dialogFlag;
    }
  },
  filters: {
    getName(name) {
      if (name.length > 15) {
        return name.substring(0, 14);
      } else {
        return name;
      }
    },
    gettime(time) {
      if (!time) {
        return "";
      }
      var nd = new Date(time);
      var y = nd.getFullYear();
      var mm = nd.getMonth() + 1;
      var d = nd.getDate();
      var h = nd.getHours();
      var m = nd.getMinutes();
      var c = nd.getSeconds();
      if (mm < 10) {
        mm = "0" + mm;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (h < 10) {
        h = "0" + h;
      }
      if (m < 10) {
        m = "0" + m;
      }
      if (c < 10) {
        c = "0" + c;
      }
      //17:35:2922-06-2022
      return h + ":" + m + ":" + c;
    },
    getTimeYear(time) {
      if (!time) {
        return "";
      }
      var nd = new Date(time);
      var y = nd.getFullYear();
      var mm = nd.getMonth() + 1;
      var d = nd.getDate();
      var h = nd.getHours();
      var m = nd.getMinutes();
      var c = nd.getSeconds();
      if (mm < 10) {
        mm = "0" + mm;
      }
      if (d < 10) {
        d = "0" + d;
      }
      if (h < 10) {
        h = "0" + h;
      }
      if (m < 10) {
        m = "0" + m;
      }
      if (c < 10) {
        c = "0" + c;
      }
      //17:35:2922-06-2022
      return y + '-' + mm + '-' + d + ' ' + h + ":" + m + ":" + c;
    }
  }
};
</script>

<style scoped lang="less">
.tr_list_page {
  width: 100%;
  height: calc(100% - 1.3rem);
  background: #fff;

  >.content {
    width: 100%;
    height: 100%;
  }
}

.tabs {
  width: 100%;
  height: 100%;
  position: relative;

  .search {
    width: 100%;
    height: 1rem;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 0.3rem;
    position: relative;

    .search_content {
      width: 100%;
      height: 90%;
      display: flex;
      align-items: center;
    }

    .left_search {
      width: 100%;
      height: 100%;
      background: #f6f6f6;
      border-radius: 0.15rem;
      display: flex;

      .search_img {
        width: 1rem;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;

        img {
          width: 0.5rem;
          height: 0.5rem;
        }
      }

      .search_input {
        width: 100%;
        overflow: hidden;
        height: 100%;
        display: flex;
      }
    }

    .right_search_class {
      width: 13%;
      height: 100%;
      display: flex;
      justify-content: flex-end;
      align-items: center;

      img {
        width: 0.6rem;
        height: 0.6rem;
      }
    }
  }
}

.top_title {
  width: 100%;
  height: 1.2821rem;
  padding: 0 0.3rem;
  position: fixed;
  background: #fff;
  top: 0;
  z-index: 2000;
  transition: all 0.5s;
  overflow: hidden;
  border-radius: 0 0 0.2rem 0.2rem;

  >div {
    width: 100%;
    height: 1.2821rem;
    display: flex;
    // align-items: center;
    justify-content: space-between;
  }

  .tabs_title {
    // width: 26%;
    height: 100%;
    display: flex;
  }

  .tb {
    // width: 50%;
    padding: 0 0.2rem;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.4103rem;
  }

  .active {
    font-size: 0.5003rem;
    font-weight: 800;
  }
}

.bottom_content {
  width: 100%;
  height: calc(100% - 1.2821rem);
  position: relative;
  top: 7%;
}

.scroll_tab {
  width: 100%;
  height: 1rem;
  overflow-x: scroll;
  -webkit-overflow-scrolling: touch;
  display: -webkit-box;
  white-space: nowrap;

  >div {
    display: inline-block;
    // width: 15%;
    height: 100%;
    display: flex;
    font-size: 0.4rem;
    align-items: center;
    padding: 0 0.3rem;

    span {
      display: inline-block;
      min-width: 40%;
      height: 100%;
      text-align: center;
      line-height: 1.1538rem;
    }
  }

  span.active {
    color: rgb(2, 99, 226);
    border-bottom: 0.07rem solid rgb(2, 99, 226);
    font-weight: 800;
    transition: all 0.5s;
  }
}

.scroll_tab::-webkit-scrollbar {
  display: none;
}

.kai_mess {
  width: 100%;
  height: 1.2821rem;
  background: #f5f9fe;
  display: flex;
  justify-content: space-between;
  position: absolute;

  .left_identity_img {
    width: 0.9744rem;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 0.2rem;

    img {
      width: 0.6rem;
      height: 0.6rem;
    }
  }

  .text {
    width: 80%;
    height: 100%;
    display: flex;
    align-items: center;
    color: rgb(2, 99, 226);
    font-weight: 800;
    font-size: 0.35rem;
    padding-left: 0.35rem;
  }

  .right_go {
    width: 10%;
    display: flex;
    align-items: center;
    justify-content: center;

    img {
      width: 0.6rem;
      height: 0.6rem;
    }
  }
}

.list::-webkit-scrollbar {
  display: none;
}

.list {
  width: 100%;

  overflow-x: auto;
  padding-bottom: 1.2821rem;
  padding: 0 0.3rem;

  .list_items {
    margin-top: -0.5rem;
    width: 100%;

    >div {
      width: 100%;
      height: 1.0256rem;
      display: flex;
      margin: 0.5rem 0;

      >div {
        width: 100%;
        height: 1.0256rem;
        display: flex;

        >div {
          >div {
            display: flex;
            align-items: center;
          }

          .tp {
            width: 100%;
            height: 70%;
          }

          .bt {
            width: 100%;
            height: 30%;

          }
        }
      }
    }

    .left_title {
      width: 45%;
      height: 100%;
    }

    .center_price {
      width: 35%;
      height: 100%;
    }

    .right_bs {
      width: 20%;
      height: 100%;
    }
  }
}

.list_title {
  width: 100%;
  height: 1rem;
  margin-top: 0.29rem;
  padding: 0 0.3rem;
  color: #a1a2a4;
  display: flex;

  >div {
    display: flex;
    align-items: center;
  }

  .varieties {
    width: 45%;
  }

  .latest_price {
    width: 35%;
  }

  .applies {
    width: 20%;
    justify-content: flex-end;
  }
}

.price_color {
  //color: rgb(39, 171, 99);
  font-size: 0.4rem;
  font-weight: 600;
}

.title_color {
  color: rgb(2, 2, 2);
  font-size: 0.4rem;
  font-weight: 600;
}

.tab_items {
  font-size: 0.2rem;
}

.tab_class {
  padding: 0 0.3rem;
}

.searchs::placeholder {
  color: #8e8f92;
}

.searchs {
  width: 100%;
}

.collection {
  display: inline-block;
  width: 0.1rem;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 0.1rem;

  .shu {
    width: 60%;
    height: 100%;
    background: #919191;
  }
}

.right_money {
  width: 44%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .content_money {
    // width: 80%;
    height: 80%;
  }

  .top_price {
    width: 100%;
    height: 70%;
    display: flex;
    align-items: center;

    .left {
      // width: 0.8rem;
      height: 0.4103rem;
      border-radius: 0.1rem;
      background: #4d73b1;
      color: #fff;
      font-size: 0.3077rem;
      display: flex;
      align-items: center;
      justify-content: center;

      span {
        display: inline-block;
        transform: scale(0.8);
        font-weight: 600;
      }
    }

    .right {
      width: auto;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: flex-end;
      white-space: nowrap;
      margin-left: 0.1rem;
      margin-right: 0.1rem;
      min-width: 1.9rem;

      // padding-left: 0.2rem;
      span {
        font-weight: 600;
        text-align: right;
      }
    }
  }

  .bottom_balance {
    width: 100%;
    height: 30%;
    display: flex;
    justify-content: flex-end;
    font-size: 0.3077rem;
    color: #acaeaf;
    transform: scale(0.9);
    margin-left: 0.2rem;
    padding-right: 0.68rem;
  }
}

.sanjiao {
  width: 0.4rem;
  height: 0.4rem;
  display: flex;
  align-items: center;
  justify-content: center;

  img {
    width: 0.4rem;
    height: 0.4rem;
  }
}

.dialog {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  top: 7%;
  z-index: 9;
  transition: all 0.5s;
}

.dialog.open {
  top: 7%;
}

.shai {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  top: 0%;
  z-index: 10;
  transition: all 0.5s;
}

.shai.open {
  top: 4%;
}

.cards {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.card_item {
  width: 48%;
  // height: 1.5385rem;
  background: #f6f6f7;
  border-radius: 0.2rem;
  margin-top: 0.2rem;
  padding: 0.4rem 0;

  .tops_title {
    width: 100%;
    // height: 40%;
    display: flex;
    align-items: flex-end;
    padding-left: 0.5rem;
  }

  .bottom_price {
    width: 100%;
    height: 60%;
    display: flex;
    align-items: center;
    padding-left: 0.5rem;
    padding-top: 0.1rem;

    p {
      font-weight: 600;
    }

    span {
      font-weight: 600;
    }
  }
}

.shublue {
  background: #0263e2 !important;
}

.xuanz {
  //旋转
  transform: rotate(180deg);
  transition: all 0.5s;
}

.red {
  color: #ff0000;
}

.green {
  color: #27ab63;
}

.mrt {
  // margin-right: 0.35rem;
}

.codeIcon {
  width: 0.6rem;
  height: 0.6rem;
  background-color: #0263e2;
  color: #fff;

  border-radius: 0.05rem;
  margin-left: 0.1rem;
  //缩放
  transform: scale(0.8);
  display: flex;
  justify-content: center;
  align-items: center;
}

.listHeight {
  height: calc(100% - 1.2821rem - 1rem - 1.1538rem - 1.3333rem);
}

.listHeights {
  height: calc(100% - 1rem - 1.1538rem - 1.3333rem);
}

.zxlist {
  height: calc(100% - 1rem - 1rem);
  padding-bottom: 0.2rem;
}

/deep/.van-list__loading {
  margin-top: 0.2rem;
}

.hongse {

  background-color: #4d73b1 !important;
}

.xgsgType {
  position: relative;
  top: 30%;
  left: 0;
  right: 0;
  margin: auto;
  width: 85%;
  height: 80%;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 0.1rem;
  background-color: rgba(102, 204, 153, 0.1);
  border: 0.04rem solid #5CE398;
  color: #5CE398;
}

.xgsgTypeRed {
  position: relative;
  top: 30%;
  left: 0;
  right: 0;
  margin: auto;
  width: 85%;
  height: 80%;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 0.1rem;
  background-color: rgba(223, 59, 59, 0.062);
  border: 0.04rem solid rgb(231, 61, 61);
  color: rgb(231, 61, 61);
}

.setting_content {
  width: 100%;
  height: 5rem;
  padding: 0.3rem;

  .old_password {
    width: 100%;
    height: 1.6rem;
    background: rgb(243, 243, 243);
    border-radius: 0.15rem;
    display: flex;
    margin-top: 0.8rem;

    .left_titles {
      margin-left: 0.2rem;
      width: 25%;
      height: 100%;
      display: flex;
      align-items: center;
      padding-left: 0.2rem;
      font-size: 0.3975rem;

      // justify-content: flex-end;
      span {
        font-weight: 600;
        letter-spacing: 0.04rem;
      }
    }

    .right_password_input {
      width: 75%;
      height: 100%;
      display: flex;
      align-items: center;

      input {
        width: 100%;
        height: 100%;
        padding-left: 0.2rem;
        border-radius: 0 0.2rem 0.2rem 0;
        font-size: 0.3975rem;
        font-weight: 600;
        //文字间距
        letter-spacing: 0.04rem;
      }
    }
  }

  .btn_setting {
    width: 100%;
    height: 1.3rem;
    border-radius: 0.15rem;
    background: #2d6ae9;
    color: #fff;
    font-size: 0.4615rem;
    margin-top: 0.5rem;
    display: flex;
    align-items: center;
    justify-content: center;

    span {
      font-weight: 600;
    }
  }
}

.shijian {
  width: 100%;
  height: 1.6rem;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

.xgsj {
  font-size: 0.3875rem;
  font-weight: 600;
  display: flex;
  height: 50%;
  align-items: center;

}

.sjtlt {
  width: 25%;
  margin-left: 0.4rem;
}

.xgTime() {
  width: 75%;
}

/deep/.van-popup {
  border-radius: 0.2rem 0.2rem 0 0;
}
</style>