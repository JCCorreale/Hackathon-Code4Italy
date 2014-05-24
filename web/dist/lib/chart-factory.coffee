# dependencies: [d3, nv]

ChartFactory = (() ->

  @createLineChart = (target, model) ->
    nv.addGraph(() ->
      chart = nv.models.lineChart()
        .x((d) -> d[0])
        .y((d) -> d[1])
        .color(d3.scale.category10().range())
        .useInteractiveGuideline(true)

      chart.xAxis
        .axisLabel('Periodo')
        .tickFormat((d) -> return model.xAxisLabels[d]) #TODO generalizzare

      chart.yAxis
        .axisLabel('Occorrenze')
        # .tickFormat(d3.format('.02f')) #TODO generalizzare

      target # svg element
        .datum(model.series)
        .transition().duration(500)
        .call(chart)

      nv.utils.windowResize(chart.update)

      return chart
    )

  return @
)()
