# dependencies: [d3, nv, nv-extensions]

ChartFactory = (() ->

  cf = createLineChart: (target, model) ->

    #TODO chart update not working properly
    # if(model.changed?)
    #   model.changed.addHandler -> 
    #     target.select(".nv-lineChart").remove()
    #     cf.createLineChart target, model
    #     return

    nv.addGraph(() ->
      chart = nv.models.lineChart()
        .x((d) -> d[0])
        .y((d) -> d[1])
        .color(d3.scale.category10().range())
        .useInteractiveGuideline(true)

      chart.margin({right: 50, bottom: 100})

      chart.xAxis
        .axisLabel('Periodo')
        .tickFormat((d) -> model.xAxisLabels[d]) #TODO generalizzare
        .rotateLabels(45)

      chart.yAxis
        .axisLabel('Occorrenze')
        # .tickFormat(d3.format('.02f')) #TODO generalizzare

      target # svg element
        .datum(model.series)
        .transition().duration(500)
        .call(chart)

      nv.utils.events.windowResize.addHandler(chart.update)

      #TODO not working
      # nv.utils.events.windowResize.addHandler((e) ->
      #   small = parseInt(target.style("width")) < 800
        
      #   chart.xAxis.rotateLabels(if small then 15 else 0)
      # )

      return chart
    )

  return cf
)()
