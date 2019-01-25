module TestHelpers
  module JsonResponse
    def json_body
      JSON.parse(response.body, symbolize_names: true)
    end

    def ids
      value.map { |item| item[:id] }
    end

    def first_element
      value.first
    end

    def value
      _key, value = json_body.first
      value
    end

    def attribute(attribute)
      first_element[attribute]
    end

    delegate :length, to: :value
  end
end
