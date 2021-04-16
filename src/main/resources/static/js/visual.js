$.ajax({
	type:"post",
	url:"/dateBill/getDateBillListByAllYear?token="+localStorage.getItem("token"),
	contentType:'application/json',
	data:JSON.stringify({}),
	success:function(res){
		if(res.status=="01"){
			var time=[];
			for(var i=4;i>=0;i--){
				time.push(new Date().getFullYear()-i)
			}
			var timeArry = new Map;
			for(var i=0;i<res.rows.length;i++){
				timeArry.set(res.rows[i].createTime+res.rows[i].status,res.rows[i].sum)
			}
			var shouru=[],zhichu=[];
			for(var i=0;i<time.length;i++){
				if(timeArry.get(time[i]+'0') != null){
					zhichu.push(timeArry.get(time[i]+'0'))
				}else{
					zhichu.push(0)
				}
				if(timeArry.get(time[i]+'1')){
					shouru.push(timeArry.get(time[i]+'1'))
				}else{
					shouru.push(0)
				}
			}
			//收支统计
			var data = {
				id: 'multipleBarsLines',
				legendBar: ['收入', '支出'],
				symbol: ' ', //数值是否带百分号        --默认为空 ''
				xAxis:time,
				yAxis: [
					shouru,
					zhichu
				],
				barColor: ['#009883', '#e66922'], //柱子颜色 必填参数
			}
			
			var myData = (function test() {
				let yAxis = data.yAxis || []
				let lines = data.lines || []
				let legendBar = data.legendBar || []
				let legendLine = data.legendLine || []
				var symbol = data.symbol || ' '
				let seriesArr = []
				let legendArr = []
				yAxis && yAxis.forEach((item, index) => {
					legendArr.push({
						name: legendBar && legendBar.length > 0 && legendBar[index]
					})
					seriesArr.push({
						name: legendBar && legendBar.length > 0 && legendBar[index],
						type: 'bar',
						barGap: '0.5px',
						data: item,
						barWidth: data.barWidth || 12,
						label: {
							normal: {
								show: true,
								formatter: '{c}' + symbol,
								position: 'top',
								textStyle: {
									color: '#fff',
									fontStyle: 'normal',
									fontFamily: '微软雅黑',
									textAlign: 'left',
									fontSize: 11,
								},
							},
						},
						itemStyle: { //图形样式
							normal: {
								barBorderRadius:0,
								borderWidth:1,
								borderColor:'#ddd',
								color: data.barColor[index]
							},
						}
					})
				})
				return {
					seriesArr,
					legendArr
				}
			})()
			option1 = {
				title: {
					show: true,
					text: data.title,
					subtext: data.subTitle,
					link: '1111'
				},
				tooltip: {
					trigger: 'axis',
					formatter: function(params) {
						var time = '';
						var str = '';
						for (var i of params) {
							time = i.name.replace(/\n/g, '') + '<br/>';
							if (i.data == 'null' || i.data == null) {
								str += i.seriesName + '：无数据' + '<br/>'
							} else {
								str += i.seriesName + '：' + i.data + symbol + '%<br/>'
							}
							
						}
						return time + str;
					},
					axisPointer: {
						type: 'none'
					},
				},
				legend: {
					right: data.legendRight || '30%',
					top: 0,
					right:10,
					itemGap: 16,
					itemWidth: 10,
					itemHeight: 10,
					data: myData.legendArr,
					textStyle: {
						color: '#fff',
						fontStyle: 'normal',
						fontFamily: '微软雅黑',
						fontSize: 12,
					}
				},
				grid: {
					x: 0,
					y: 30,
					x2: 0,
					y2: 25,
				},
				xAxis: {
					type: 'category',
					data: data.xAxis,
					axisTick: {
						show: false,
					},
					
					axisLine: {
						show: false,
					},
					axisLabel: {       //轴标
						show: true,
						interval: 0,
						textStyle: {
							lineHeight:5,
							padding: [2, 2, 0, 2],
							height: 50,
							fontSize: 12,
							color:'#fff',
						},
						rich: {
							Sunny: {
								height: 50,
								// width: 60,
								padding: [0, 5, 0, 5],
								align: 'center',
							},
						},
						formatter: function(params, index) {
							var newParamsName = "";
							var splitNumber = 5;
							var paramsNameNumber = params && params.length;
							if (paramsNameNumber && paramsNameNumber <= 4) {
								splitNumber = 4;
							} else if (paramsNameNumber >= 5 && paramsNameNumber <= 7) {
								splitNumber = 4;
							} else if (paramsNameNumber >= 8 && paramsNameNumber <= 9) {
								splitNumber = 5;
							} else if (paramsNameNumber >= 10 && paramsNameNumber <= 14) {
								splitNumber = 5;
							} else {
								params = params && params.slice(0, 15);
							}
							
							var provideNumber = splitNumber; //一行显示几个字
							var rowNumber = Math.ceil(paramsNameNumber / provideNumber) || 0;
							if (paramsNameNumber > provideNumber) {
								for (var p = 0; p < rowNumber; p++) {
									var tempStr = "";
									var start = p * provideNumber;
									var end = start + provideNumber;
									if (p == rowNumber - 1) {
										tempStr = params.substring(start, paramsNameNumber);
									} else {
										tempStr = params.substring(start, end) + "\n";
									}
									newParamsName += tempStr;
								}
								
							} else {
								newParamsName = params;
							}
							params = newParamsName
							return '{Sunny|' + params + '}';
						},
						color: '#687284',
					},
					
				},
				yAxis: {
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						show: false
					},
					splitLine: {
						show: true,
						lineStyle: {
							color: '#F1F3F5',
							type: 'solid'
						},
						interval: 2
					},
					splitNumber: 4,
				},
				series: myData.seriesArr
			}
			//////////////////////收支统计 end
			//交通流量
			var myChart1 = echarts.init(document.getElementById('main1'));
		    myChart1.setOption(option1);
		}
	}
})

$.ajax({
	type:"post",
	url:"/dateBill/getDateBillListByAllMonth?token="+localStorage.getItem("token"),
	contentType:'application/json',
	data:JSON.stringify({billDate:new Date().getFullYear()+"-01-01"}),
	success:function(res){
		if(res.status=="01"){
			var time=[];
			for(var i=1;i<=12;i++){
				if(i >= 10){
					time.push(new Date().getFullYear()+"-"+i)
				}else{
					time.push(new Date().getFullYear()+"-0"+i)
				}
			}
			var timeArry = new Map;
			for(var i=0;i<res.rows.length;i++){
				timeArry.set(res.rows[i].month+res.rows[i].status,res.rows[i].sum)
			}
			var shouru=[],zhichu=[];
			for(var i=0;i<time.length;i++){
				if(timeArry.get(time[i]+'0') != null){
					zhichu.push(timeArry.get(time[i]+'0'))
				}else{
					zhichu.push(0)
				}
				if(timeArry.get(time[i]+'1')){
					shouru.push(timeArry.get(time[i]+'1'))
				}else{
					shouru.push(0)
				}
			}
			//收支类型统计
			option2 = {
					tooltip: {//鼠标指上时的标线
						trigger: 'axis',
						axisPointer: {
							lineStyle: {
								color: '#fff'
							}
						}
					},
					legend: {
						icon: 'rect',
						itemWidth: 14,
						itemHeight: 5,
						itemGap: 13,
						data: ['收入', '支出'],
						right: '10px',
						top: '0px',
						textStyle: {
							fontSize: 12,
							color: '#fff'
						}
					},
					grid: {
						x: 35,
						y: 25,
						x2: 8,
						y2: 25,
					},
					xAxis: [{
						type: 'category',
						boundaryGap: false,
						axisLine: {
							lineStyle: {
								color: '#57617B'
							}
						},
						axisLabel: {
							textStyle: {
								color:'#fff',
							},
						},
						data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
					}],
					yAxis: [{
						type: 'value',
						axisTick: {
							show: false
						},
						axisLine: {
							lineStyle: {
								color: '#57617B'
							}
						},
						axisLabel: {
							margin: 10,
							textStyle: {
								fontSize: 14
							},
							textStyle: {
								color:'#fff',
							},
						},
						splitLine: {
							lineStyle: {
								color: '#57617B'
							}
						}
					}],
					series: [{
						name: '收入',
						type: 'line',
						smooth: true,
						lineStyle: {
							normal: {
								width: 2
							}
						},
						areaStyle: {
							normal: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
									offset: 0,
									color: 'rgba(137, 189, 27, 0.3)'
								}, {
									offset: 0.8,
									color: 'rgba(137, 189, 27, 0)'
								}], false),
								shadowColor: 'rgba(0, 0, 0, 0.1)',
								shadowBlur: 10
							}
						},
						itemStyle: {
							normal: {
								color: 'rgb(137,189,27)'
							}
						},
						data: shouru
					}, {
						name: '支出',
						type: 'line',
						smooth: true,
						lineStyle: {
							normal: {
								width: 2
							}
						},
						areaStyle: {
							normal: {
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
									offset: 0,
									color: 'rgba(0, 136, 212, 0.3)'
								}, {
									offset: 0.8,
									color: 'rgba(0, 136, 212, 0)'
								}], false),
								shadowColor: 'rgba(0, 0, 0, 0.1)',
								shadowBlur: 10
							}
						},
						itemStyle: {
							normal: {
								color: 'rgb(0,136,212)'
							}
						},
						data: zhichu
					}]
			};
			//收支类型统计
			var myChart2 = echarts.init(document.getElementById('main2'));
			myChart2.setOption(option2);
			//////////////////////收支类型统计end
		}
	}
})
function loadDataByBillType(billType){
	var type = null;
	if(billType == "全部类型"){
		type = ""
	}else{
		type = billType
	}
	//今日实时收费
	$.ajax({
		type:"post",
		url:"/dateBill/getDateBillListByAllBillType?token="+localStorage.getItem("token"),
		contentType:'application/json',
		data:JSON.stringify({billDate:"当天",billType:type}),
		success:function(res){
			if(res.status=="01"){
				var shadowColor = '#374b86';
				var income = res.rows[0].income;
				option5 = {
					title: {
						//text: `${value}万辆`,
						text: `收入`,
						subtext: '',
						left: 'center',
						top: 'center',//top待调整
						textStyle: {
							color: '#fff',
							fontSize: 16,
							fontFamily: 'PingFangSC-Regular'
						},
						subtextStyle: {
							color: '#ff',
							fontSize: 14,
							fontFamily: 'PingFangSC-Regular',
							top: 'center'
						},
						itemGap: -1//主副标题间距
					},
					
					series: [{
						name: 'pie1',
						type: 'pie',
						clockWise: true,
						radius: ['65%', '70%'],
						itemStyle: {
							normal: {
								label: {
									show: false
								},
								labelLine: {
									show: false
								}
							}
						},
						hoverAnimation: false,
						data: [{
							value: income,
							name: 'completed',
							itemStyle: {
								normal: {
									borderWidth: 8,
									borderColor: { 
										colorStops: [{
											offset: 0,
											color: '#1d54f7' || '#00cefc' // 0% 处的颜色
										}, {
											offset: 1,
											color: '#68eaf9' || '#367bec' // 100% 处的颜色
										}]
									},
									color: { // 完成的圆环的颜色
										colorStops: [{
											offset: 0,
											color: '#1d54f7' || '#00cefc' // 0% 处的颜色
										}, {
											offset: 1,
											color: '#68eaf9' || '#367bec' // 100% 处的颜色
										}]
									},
									label: {
										show: true
									},
									labelLine: {
										show: false
									}
								}
							}
						}, {
							name: 'gap',
							value: 100 - income,
							itemStyle: {
								normal: {
									label: {
										show: false
									},
									labelLine: {
										show: false
									},
									color: 'rgba(0, 0, 0, 0)',
									borderColor: 'rgba(0, 0, 0, 0)',
									borderWidth: 0
								}
							}
						}]
					}]
			}
				
			var shadowColor = '#374b86';
			var pay = res.rows[0].pay;
			option6 = {
					title: {
						//text: `${value}万辆`,
						text: `支出`,
						subtext: '',
						left: 'center',
						top: 'center',//top待调整
						textStyle: {
							color: '#fff',
							fontSize: 16,
							fontFamily: 'PingFangSC-Regular'
						},
						subtextStyle: {
							color: '#ff',
							fontSize: 14,
							fontFamily: 'PingFangSC-Regular',
							top: 'center'
						},
						itemGap: -1//主副标题间距
					},
					
					series: [{
						name: 'pie1',
						type: 'pie',
						clockWise: true,
						radius: ['65%', '70%'],
						itemStyle: {
							normal: {
								label: {
									show: false
								},
								labelLine: {
									show: false
								}
							}
						},
						hoverAnimation: false,
						data: [{
							value: pay,
							name: 'completed',
							itemStyle: {
								normal: {
									borderWidth: 8,
									borderColor: { 
										colorStops: [{
											offset: 0,
											color: '#02df94' || '#25d6bc' // 0% 处的颜色
										}, {
											offset: 1,
											color: '#28d3d0' || '#14dbaa' // 100% 处的颜色
										}]
									},
									color: { // 完成的圆环的颜色
										colorStops: [{
											offset: 0,
											color: '#02df94' || '#25d6bc' // 0% 处的颜色
										}, {
											offset: 1,
											color: '#28d3d0' || '#14dbaa' // 100% 处的颜色
										}]
									},
									label: {
										show: false
									},
									labelLine: {
										show: false
									}
								}
							}
						}, {
							name: 'gap',
							value: 100,
							itemStyle: {
								normal: {
									label: {
										show: true
									},
									labelLine: {
										show: false
									},
									color: 'rgba(0, 0, 0, 0)',
									borderColor: 'rgba(0, 0, 0, 0)',
									borderWidth: 0
								}
							}
						}]
					}]
				}
				//今日实时收费
				var myChart5 = echarts.init(document.getElementById('main5'));
				myChart5.setOption(option5);
				var myChart6 = echarts.init(document.getElementById('main6'));
				myChart6.setOption(option6);
				// var myChart7 = echarts.init(document.getElementById('main7'));
				//myChart7.setOption(option7);
			}
		}
	})
}
//////////////////////今日实时收费 end
